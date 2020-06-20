# coding: utf-8

from types import MethodType


def set_age(self, age):
    self.age = age


def set_score(self, score):
    self.score = score


class Student(object):
    def get_name(self):
        return self.name


Student.set_score = set_score

s = Student()
s.name = 'Michael'
print(s.name)
print(Student.get_name(s))
s.set_age = MethodType(set_age, s)
s.set_age(25)
print(s.age)
s.set_score(100)
print(s.score)

s2 = Student()
# s2.set_age(25) # raise AttributeError: 'Student' object has no attribute 'set_age'
s2.set_score(99)
print(s2.score)
