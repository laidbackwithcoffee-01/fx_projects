# -*- coding: utf-8 -*-

import os
import requests
import json
import psycopg2
import sys
sys.path.append(os.path.dirname(os.path.abspath(__file__)) + '/utils')
import lineNotifier as ln
import getEnvValue as env

# 通過ペアを取得
def get_pairs():
    cur = conn.cursor()
    cur.execute("select \"codeName\" from msymbols")
    results = cur.fetchall()
    pairs = ''
    for r in results:
        pairs += r[0] + ','
    cur.close()
    return pairs

# FX API にアクセスしてデータを取得
def get_remote(pairs, apikey):
    response = requests.get('https://forex.1forge.com/1.0.3/quotes', params={'pairs': pairs, 'api_key' : apikey})
    data_json = response.json()
    return data_json

# 取得したデータを整値
def get_data(data):
    arrs = []
    for x in data:
        arr = [x['timestamp'], x['symbol'], x['price'], x['bid'], x['ask']]
        arrs.append(arr)
    return arrs

# データ書き込み
def insert_postgresql(arrs):
    for arr in arrs:
        cur = conn.cursor()
        cur.execute("select code from msymbols where \"codeName\" = %s", (arr[1],))
        results = cur.fetchall()
        # TimeStamp, SymbolCode, price, bid, ask
        cur.execute("insert into pairsprice values(%s, %s, %s, %s, %s)", (arr[0], results[0][0], arr[2], arr[3], arr[4],))
        conn.commit()
        cur.close()
    return results

# メインロジック
if __name__ == '__main__':
    conn = psycopg2.connect(host="localhost", database="fx_projects", user="dbuser", password="abcdefg")
    pairs = get_pairs()
    apikey = env.get_apikey()
    data_json = get_remote(pairs, apikey)
    arrs = get_data(data_json)
    insert_postgresql(arrs)
    conn.close()
    ln.lineNotifier("正常に為替情報の取得を行いました。")
