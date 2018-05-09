# -*- coding: utf-8 -*-

from enum import IntEnum

class Sqltype(IntEnum):
    SELECT = 0
    UPDATE = 1
    INSERT = 2
    DELETE = 3
