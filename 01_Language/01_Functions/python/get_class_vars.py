# coding: utf-8

class TestCase(object):
    d = 1
    _e = 2
    __f = 3

    def __init__(self):
        self.a = 1
        self._b = 2
        self.__c = 3

    @classmethod
    def expose(cls):
        print(vars(cls))


TestCase.expose()
print(vars(TestCase))
print(vars(TestCase()))
