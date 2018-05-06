# -*- coding: utf-8 -*-

import os
import mysql.connector
import sys
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/utils')
import getEnvValue as env

CSV_FILE_PATH = os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/currency.csv'

if __name__ == '__main__':

    dbs = env.getmysqlsetting()
    conn = mysql.connector.connect(**dbs)

    file = open(CSV_FILE_PATH, "r")

    # テーブルクリア
    if conn.is_connected():
        cur = conn.cursor(dictionary=True)
        cur.execute('truncate table msymbols')
        conn.commit()
        cur.close()

        cur = conn.cursor(dictionary=True)
        for line in file.readlines():
            line = line.replace('\r', '').replace('\n', '')
            data = line.split(',')
            # データ書き込み
            cur.execute("insert into msymbols(code, codename) values(%s, %s)", (data[0], data[1]))
        conn.commit()
        cur.close()

    file.close()
    conn.close()