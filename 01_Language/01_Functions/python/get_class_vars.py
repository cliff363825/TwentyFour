# coding: utf-8

def get_class_vars(cls):
    return vars(cls)


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
        print(get_class_vars(cls))


if __name__ == '__main__':
    TestCase.expose()
    print("================")
    print(get_class_vars(TestCase))
