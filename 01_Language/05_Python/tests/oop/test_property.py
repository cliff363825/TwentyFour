# coding: utf-8

class Student(object):
    def __init__(self):
        self._score = 0
        self._age = 0

    # score = property(score)
    @property
    def score(self):
        return self._score

    # score = score.setter(score)
    @score.setter
    def score(self, score):
        self._score = score

    def get_age(self):
        return self._age

    def set_age(self, age):
        self._age = age

    age = property(get_age, set_age)


stu1 = Student()
stu1.score = 100
print(stu1.score)

stu1.age = 15
print(stu1.age)
