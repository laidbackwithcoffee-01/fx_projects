# -*- coding: utf-8 -*-

import os
import mysql.connector
import const
import sys
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/utils')
import getEnvValue as env

from datetime import datetime

# 定数
const.DIR_BACKUP = os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/backup/'
const.STR_START = 'COPY public.pairsprice ("timestamp", symbolcode, price, bid, ask) FROM stdin;'
const.STR_END = '\\.'
const.COL_TIMESTAMP = 0
const.COL_SYMBOLCODE = 1
const.COL_PRICE = 2
const.COL_BID = 3
const.COL_ASK = 4

def getAlreadyImportFiles():
    """
    取り込み済みファイル名取得

    Returns
    ----------
        filenames : array(string)
            取り込み済みファイル名
    """
    cur = conn.cursor(dictionary=True)
    stmt = 'select filename from importhist order by filename'
    cur.execute(stmt)
    list = cur.fetchall()
    filenames = []
    for v in list:
        filenames.append(v['filename'])
    return filenames

def convDict(files_file, avoid_files):
    """
    取り込み対象データの構築

    Parameters
    ----------
    files_file : array(string)
        Backupディレクトリ配下のファイル一覧
    avoid_files : array(string)
        取り込み対象外ファイルリスト

    Returns
    ----------
        dict : dictionary
            取り込みデータ
    """
    dict = {}
    for fn in files_file:
        # ファイル名の１文字目が「p」
        if fn[1-1] == 'p':
            # 引数 avoid_files に含まれているファイルは避ける
            if not fn in avoid_files:
                flag = False
                arrays = []
                f = open(const.DIR_BACKUP + fn, "r")
                for l in f:
                    # 改行コードを除去
                    l = l.replace('\n', '').replace('\r', '')
                    if l == const.STR_END:
                        flag = False
                    if flag == True:
                        array = l.split('\t')
                        arrays.append(array)
                    if l == const.STR_START:
                        flag = True
                f.close
                dict[fn] = arrays

    return dict

def importData(import_data):
    """
    取り込み処理

    Parameters
    ----------
    import_data : dictionary
        取り込みデータ
    """
    for k in import_data.keys():
        # ファイル毎にトランザクションコントロールを行う。
        cur = conn.cursor(dictionary=True)
        datas = import_data[k]
        try:
            for array in datas:
                stmt = "insert into pairsprice(timestamp, symbolcode, price, bid, ask) values(%s, %s, %s, %s, %s)"
                cur.execute(stmt, (
                    array[const.COL_TIMESTAMP],
                    array[const.COL_SYMBOLCODE],
                    array[const.COL_PRICE],
                    array[const.COL_BID],
                    array[const.COL_ASK],
                ))
            stmt = "insert into importhist(filename, importtimestamp) values(%s, %s)"
            cur.execute(stmt, (
                k,
                datetime.now().strftime('%Y-%m-%d %H:%M:%S')
            ))
            conn.commit()
            print("Completed import file:" + k + " ...")
        except Exception as e:
            conn.rollback()
            raise e
        finally:
            cur.close()

if __name__ == '__main__':

    # 設定ファイル読み取り
    dbs = env.getmysqlsetting()
    conn = mysql.connector.connect(**dbs)

    if conn.is_connected:
        # 既に取り込み済みファイルを取得
        alreadyimportedfiles = getAlreadyImportFiles()

        # バックアップディレクトリ直下のファイルを取得
        files = os.listdir(const.DIR_BACKUP)
        files_file = [f for f in files if os.path.isfile(os.path.join(const.DIR_BACKUP, f))]
        files_file.sort()

        # 取り込み処理
        importData(convDict(files_file, alreadyimportedfiles))

        conn.close()
        print("為替情報の取り込み処理に成功しました。")

    else:
        print('Databaseに接続できませんでした。')
