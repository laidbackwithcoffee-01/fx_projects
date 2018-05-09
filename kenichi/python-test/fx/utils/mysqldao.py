# -*- coding: utf-8 -*-

import os
import mysql.connector
import Sqltype as sqt
import sys
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/utils')
import getEnvValue as env

class mysqldao:

    def __init__(self):
        """
        Constructor
        """
        # MySQL DB設定
        self.__dbs = env.getmysqlsetting()
        self.__con = mysql.connector.connect(**self.__dbs)
        self.__debugmode = False

    def close(self):
        """
        close connection
        """
        self.__con.close()
        self.__dbs.clear()

    def setdebugmode(self, mode):
        """
        set debug mode

        Parameters
        ----------
            mode : boolean
                debug mode
        """
        self.__debugmode = mode

    def __constructsql(self, sqltype, **sql):
        """
        Construct Sql

        Parameters
        ----------
            sqltype : Sqltype
                SELECT, INSERT, UPDATE, DELETE
            **sql : dictionary
                sql informations
        
        Returns
        ----------
            stmt : string
                constructed sql
        """
        if sql.__contains__('table'):
            tbl = sql['table']
        else:
            raise Exception('input table infomation.')
        if sql.__contains__('where'):
            whr = " where " + sql['where']
        else:
            whr = ''
        if sql.__contains__('order'):
            ord = " order by " + sql['order']
        else:
            ord = ''
        if sql.__contains__('select'):
            sel = sql['select']
        else:
            sel = '*'
            
        if sqltype == sqt.Sqltype.SELECT:
            stmt = "select " + sel + " from " + tbl + whr + ord
        elif sqltype == sqt.Sqltype.INSERT:
            if sql.__contains__('values'):
                vals = " values(" + sql['values'] + ")"
            else:
                raise Exception('input values infomation.')
            stmt = 'insert into ' + tbl + vals
        elif sqltype == sqt.Sqltype.UPDATE:
            if sql.__contains__('set'):
                st = sql['set']
            else:
                raise Exception('input set information.')
            stmt = 'update ' + tbl + ' set ' + st + whr
        else:
            stmt = 'delete from ' + tbl + whr
        
        if self.__debugmode:
            print(stmt)

        return stmt

    def executequery(self, **sql):
        """
        select sql execute

        Parameters
        ----------
            **sql : dictionary
                select, table, where, order

        Returns
        ----------
            result : array(array)
                result of select sql execution
        """
        self.__cur = self.__con.cursor()
        self.__cur.execute(self.__constructsql(sqt.Sqltype.SELECT, **sql))
        return self.__cur.fetchall()
    
    def executequerysql(self, sql):
        """
        select sql execute

        Parameters
        ----------
            sql : string
                the sql you want to execute

        Returns
        ----------
            result : array(array)
                result of select sql execution
        """
        self.__cur = self.__con.cursor()
        self.__cur.execute(sql)
        return self.__cur.fetchall()

    def executeupdate(self, sqltype, **sql):
        """
        insert update delete sql execute

        Parameters
        ----------
            sqltype : Sqltype
                SELECT INSERT UPDATE DELETE
            **sql : dictionary
                select, table, where, order

        Returns
        ----------
            rowcount : int
                affect row count
        ----------
        """
        self.__cur = self.__con.cursor()
        stmt = self.__constructsql(sqltype, **sql)
        try:
            self.__cur.execute(stmt)
            self.__con.commit()
        except Exception as e:
            self.__con.rollback()
            raise e
        finally:
            self.__cur.close()

        return self.__cur.rowcount
    
    def executeupdatesql(self, sql):
        """
        insert update delete sql execute

        Parameters
        ----------
            sql : string
                the sql you want to execute

        Returns
        ----------
            rowcount : int
                affect row count
        """
        self.__cur = self.__con.cursor()
        try:
            self.__cur.execute(sql)
            rowcount = self.__cur.rowcount
            self.__con.commit()
        except Exception as e:
            self.__con.rollback()
            raise e
        finally:
            self.__cur.close()
        
        return rowcount

    def executeupdatemulti(self, sqls):
        """
        execute multi sqls

        Parameters
        ----------
            sqls : array(dictionary)
                multiple sqls

        Returns
        ----------
            rowcounts : array(int)
                affect row counts
        """
        self.__cur = self.__con.cursor()
        rowcounts = []
        try:
            for sql in sqls:
                sqltype = sql['sqltype']
                stmt = self.__constructsql(sqltype, **sql)
                self.__cur.execute(stmt)
                rowcounts.append(self.__cur.rowcount)
            self.__con.commit()
        except Exception as e:
            self.__con.rollback()
            raise e
        finally:
            self.__cur.close()

        return rowcounts

    def executeupdatemultisql(self, sqls):
        """
        execute multi sqls

        Parameters
        ----------
            sqls : array(string)
                multiple sqls

        Returns
        ----------
            rowcounts : array(int)
                affect row counts
        """
        self.__cur = self.__con.cursor()
        rowcounts = []
        try:
            for sql in sqls:
                self.__cur.execute(sql)
                rowcounts.append(self.__cur.rowcount)
            self.__con.commit()
        except Exception as e:
            self.__con.rollback()
            raise e
        finally:
            self.__cur.close()
        
        return rowcounts