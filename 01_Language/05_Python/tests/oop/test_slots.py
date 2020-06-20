# coding: utf-8

class Student(object):
    __slots__ = ['name', 'age']

    def __init__(self):
        pass


stu1 = Student()
stu1.name = 'lilei'
stu1.age = 16
# stu1.score = 66

stu2 = Student()
stu2.name = 'hanmeimei'
stu2.age = 15
# stu2.score = 88

# print(stu1.__dict__)
