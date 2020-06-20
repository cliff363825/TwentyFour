# coding: utf-8
import json

d = dict(name='Bob', age=20, score=88)
print(json.dumps(d))

json_str = '{"score": 88, "name": "Bob", "age": 20}'
o = json.loads(json_str)
print(o, type(o))


class Student(object):
    def __init__(self, name, age, score):
        self.name = name
        self.age = age
        self.score = score


def student2dict(stu):
    return dict(
        name=stu.name,
        age=stu.age,
        score=stu.score
    )


s = Student('Bob', 20, 88)
print(json.dumps(s, default=student2dict))
print(json.dumps(s, default=lambda obj: obj.__dict__))
print(json.dumps(vars(s)))
print(json.dumps(s.__dict__))


def dict2student(d):
    return Student(d['name'], d['age'], d['score'])


print(json.loads(json_str, object_hook=dict2student))
