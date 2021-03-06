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

        # 1時的に環境変数を追加する。
	sys.path.append(homeDir)
	import DTMDataMining
	from util import Util
	from dataBaseAccess import Dao

	# utilクラスのオブジェクト取得
	utilClass = Util.Util(pid)

	# # Daoクラスをインスタンス化
	daoClass = Dao.Dao(pid)


	# Test対象クラスのインスタンスを生成する。
	DTMDataMiningClass = DTMDataMining.DTMDataMining(pid,utilClass,daoClass)


	# VIRTUAL_CURRENCY_T に格納されている日時分を全て取得する。
	where = ["WHERE INS_PID='BTV' AND DATA_TIME > '201706081726'"," ORDER BY DATA_TIME"]
	result = daoClass.selectQuery(where,'BTV_currency_t')

	for info in result:
		condTime = info[0]

		# mainクラスを呼び出す。
		DTMDataMiningClass.dtmservice(condTime)


	DTMDataMiningClass.finish()

if __name__ == '__main__':
	main()
