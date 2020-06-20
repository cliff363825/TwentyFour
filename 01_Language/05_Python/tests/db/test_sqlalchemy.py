# coding: utf-8

from sqlalchemy import Column, String, create_engine
from sqlalchemy import ForeignKey
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base

# 创建对象的积累
Base = declarative_base()


class User(Base):
    # 表的名字
    __tablename__ = 'user'

    # 表的结构
    id = Column(String(20), primary_key=True)
    name = Column(String(20))


class Book(Base):
    __tablename__ = 'book'

    id = Column(String(20), primary_key=True)
    name = Column(String(20))
    user_id = Column(String(20), ForeignKey('user.id'))


# 初始化数据库连接
engine = create_engine('mysql+mysqlconnector://root:root@192.168.1.64:3306/test')
# 创建SessionFactory 会话工厂
DbSession = sessionmaker(bind=engine)

# 创建session会话
# session = DbSession()
# 创建新的User对象
# new_user = User(id='5', name='Bob')
# 添加到session
# session.add(new_user)
# 提交即保存到数据库
# session.commit()
# 关闭session
# session.close()

# 创建session会话
session = DbSession()
# 创建Query查询， filter是where条件， 最后调用one() 返回唯一行， 如果调用all() 返回所有行
# User.id=='5' 这里用到了运算符重载
user = session.query(User).filter(User.id == '5').one()
# 打印类型和对象的name属性
print('type:', type(user))
print('name:', user.name)
# 关闭session
session.close()
