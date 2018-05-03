# -*- coding: utf-8 -*-

import os
import requests
import getEnvValue as env

def lineNotifier(message):
    url = "https://notify-api.line.me/api/notify"
    token = env.get_token()
    headers = {"Authorization" : "Bearer "+ token}

    payload = {"message" :  message}

    r = requests.post(url ,headers = headers ,params=payload,)

    return r

def lineNotifierWithFiles(message, files):
    url = "https://notify-api.line.me/api/notify"
    token = env.get_token()
    headers = {"Authorization" : "Bearer "+ token}

    payload = {"message" :  message}
    # files = {"imageFile": open("test.jpg", "rb")} #バイナリで画像ファイルを開きます。対応している形式はPNG/JPEGです。

    r = requests.post(url ,headers = headers ,params=payload, file=files)

    return r
