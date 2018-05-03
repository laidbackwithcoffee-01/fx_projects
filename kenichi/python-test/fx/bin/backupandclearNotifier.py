# -*- coding: utf-8 -*-

import os, sys
sys.path.append(os.path.dirname(os.path.abspath(__file__)) + '/../utils')
import lineNotifier as ln

ln.lineNotifier("正常に為替情報のバックアップとテーブルのTRUNCATEを行いました。")
