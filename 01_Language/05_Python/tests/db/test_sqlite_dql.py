# coding: utf-8

import sqlite3

conn = sqlite3.connect('test.db')
cursor = conn.cursor()
# 执行查询语句
rs = cursor.execute('select * from user where id=?', (1,))
print(rs)
rs = cursor.execute('select * from user where id=?', (2,))
print(rs)
# 获得查询结果集
values = cursor.fetchall()
print(values)
cursor.close()
conn.close()
