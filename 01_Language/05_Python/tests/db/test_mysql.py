# coding: utf-8

# 安装Mysql驱动
# pip install mysql-connector-python --allow-external mysql-connector-python
# pip install mysql-connector

# 导入Mysql驱动
import mysql.connector

# 创建数据库连接
conn = mysql.connector.connect(host='192.168.1.64', user='root', password='root', database='test')
# 创建游标
cursor = conn.cursor()
# 创建user表
cursor.execute('create table user(id varchar(20) primary key, name varchar(20))')
# 插入一行数据，注意Mysql的占位符是%s
cursor.execute('insert into user(id, name) values (%s, %s)', ['1', 'Michael'])
print(cursor.rowcount)
# 关闭游标
cursor.close()
# 提交事务
conn.commit()

# 再次创建游标
cursor = conn.cursor()
# 运行查询
cursor.execute('select * from user where id=%s', ('1',))
values = cursor.fetchall()
print(values)
# 关闭游标
cursor.close()
# 关闭数据库连接
conn.close()
