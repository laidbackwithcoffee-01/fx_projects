# coding: utf-8
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time
import sys
import os
import configparser
import urllib.request
import json

def main():
	# 環境変数を取得する。
	homeDir = os.environ["APPMONEYTRADE"]

	# log用の文字列準備
	pid = os.path.basename(__file__)[0:3] # 機能IDの取得

	#引数の取得
	args = sys.argv
	condTime = args[1]

        # 1時的に環境変数を追加する。
	sys.path.append(homeDir)
	from util import Util
	from util import JsonParse
	from forge import ForgeApi
	from dataBaseAccess import Dao

	# utilクラスのオブジェクト取得
	utilClass = Util.Util(pid)

	# jsonparseクラスのオブジェクト取得
	jsonClass = JsonParse.JsonParse(pid)

	# daoクラスのオブジェクトを取得する。
	dao = Dao.Dao(pid,utilClass)

	# bitflyyerApiクラスを取得
	ForgeApiClass = ForgeApi.ForgeApi(pid,utilClass)

	# configファイルから情報を抜き出す.
	inifile = utilClass.inifile

	# キーを取得する。　
	key=inifile.get('apikey','forge')
	# 前回実行時間を取得する。
	f = open(homeDir + inifile.get(pid,'exetime'), "r")
	exetime = f.read()[0:12]
	if int(condTime) % 2 == 1:
		# 前回と同じ時間なので処理しない。
		utilClass.logging(pid + ' ' + condTime + ' is skip for before this time has done arleady',2)
		return
	if exetime == condTime:
		utilClass.logging(pid + ' ' + condTime + ' is skip for before this time has done arleady',2)
		return

	f.close()
	# バッチのログを出力する.
	utilClass.logging(pid + ' ' + condTime,0)


		
	# webapiを呼び出す。
	josnStr = ForgeApiClass.quotesApi(key)

	#ファイルを保存する。
	# json読み込みとパース
	resultDict = jsonClass.forgePairParse(josnStr)

	# boradの挿入を行う。
	forgePairInsert(resultDict,pid,condTime,dao)


	# 最後に実行時間を更新する。
	f = open(homeDir + inifile.get(pid,'exetime'), "w")
	f.write(condTime)
	

	# このバッチ処理の終了log
	utilClass.logging(pid + ' ' + condTime,1)


def forgePairInsert(insert_dict,pid,condTime,dao):
	
	# 辞書を見せる。
	insert_dict['LOGIC_DEL_FLG'] = 0
	insert_dict['INS_PID'] = pid
	insert_dict['UPD_PID'] = pid
	insert_dict['DATA_TIME'] = condTime
	# insert
	result = dao.insert('PAIR_T',insert_dict)

if __name__ == '__main__':
	main()
