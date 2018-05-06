# -*- coding: utf-8 -*-

import os
import configparser

def get_token():
    """
    line notify token の取得

    Returns
    ----------
        token : string
            Line Notify token
    """
    if os.name == 'nt':
        dataPath = r'C:\work\fx\line_notify_token'
    else:
        dataPath = '/work/fx/line_notify_token'
    with open(dataPath, 'r') as f:
        token = f.readline()
    return token

def get_apikey():
    """
    forge API KEY の取得

    Returns
    ----------
        apikey : string
            forge API KEY
    """
    if os.name == 'nt':
        dataPath = r'C:\work\fx\apikey'
    else:
        dataPath = '/work/fx/apikey'
    with open(dataPath, 'r') as f:
        apikey = f.readline()
    return apikey

def getmysqlsetting():
    """
    設定ファイル読み取り

    Returns
    ----------
        db_setting : dictionary
            mysql db setting
    """
    inifile = configparser.ConfigParser()
    inifile.read(os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/config.ini', 'UTF-8')

    db_host = inifile.get('settings', 'db_host')
    db_dbname = inifile.get('settings', 'db_dbname')
    db_port = inifile.get('settings', 'db_port')
    db_user = inifile.get('settings', 'db_user')
    db_password = inifile.get('settings', 'db_password')

    db_setting = {
        "host":db_host,
        "database":db_dbname,
        "user":db_user,
        "password":db_password,
        "port":db_port
    }

    return db_setting
