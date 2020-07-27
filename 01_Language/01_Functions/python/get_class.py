# coding: utf-8

class bar(object):
    def __init__(self):
        print(type(self))
        print(type(self).__name__)

class foo(bar):
    pass

foo()
