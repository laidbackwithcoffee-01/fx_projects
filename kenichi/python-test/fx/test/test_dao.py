# -*- coding: utf-8 -*-

import os
import sys
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/utils')
import mysqldao as md
import Sqltype as sqt

if __name__ == '__main__':

    # executeupdate delete test
    dao = md.mysqldao()
    dao.setdebugmode(True)
    sql = {'table' : 'test'}
    ret = dao.executeupdate(sqt.Sqltype.DELETE, **sql)
    dao.close()
    print(ret)

    # executeupdate insert test
    dao = md.mysqldao()
    dao.setdebugmode(True)
    sql = {'table' : 'test(id, name)', 'values' : '1, \'aaaa\''}
    ret = dao.executeupdate(sqt.Sqltype.INSERT, **sql)
    dao.close()
    print(ret)

    # executeupdatemultisql test
    dao = md.mysqldao()
    sqls = []
    for i in [2,3,4,5,6,7,8,9]:
        sqls.append("insert into test(id, name) values(" + str(i) + ", 'name" + str(i) + "')")
    rets = dao.executeupdatemultisql(sqls)
    dao.close()
    print(rets)

    # executeupdate update test
    dao = md.mysqldao()
    dao.setdebugmode(True)
    sql = {'table' : 'test', 'set' : 'name = \'fffff\'', 'where' : 'id = 8'}
    ret = dao.executeupdate(sqt.Sqltype.UPDATE, **sql)
    print(ret)

    # executequery test
    dao = md.mysqldao()
    dao.setdebugmode(True)
    sql = {'select' : '*', 'table' : 'test'}
    datas = dao.executequery(**sql)
    dao.close()
    print(datas)
