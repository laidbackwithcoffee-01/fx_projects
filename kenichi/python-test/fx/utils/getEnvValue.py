# -*- coding: utf-8 -*-

import os

# Tokenを取得
def get_token():
    if os.name == 'nt':
        dataPath = r'C:\work\fx\line_notify_token'
    else:
        dataPath = '/work/fx/line_notify_token'
    with open(dataPath, 'r') as f:
        token = f.readline()
    return token

# get forge's api key
def get_apikey():
    if os.name == 'nt':
        dataPath = r'C:\work\fx\apikey'
    else:
        dataPath = '/work/fx/apikey'
    with open(dataPath, 'r') as f:
        apikey = f.readline()
    return apikey