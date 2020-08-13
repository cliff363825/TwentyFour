# coding: utf-8

def get_object_vars(obj):
    return vars(obj)


if __name__ == '__main__':
    class foo(object):
        e = None

        def __init__(self):
            self.a = None
            self.b = 1
            self.c = None
            self.d = None

        def test(self):
            print(get_object_vars(self))


    f = foo()
    f.test()
    print(get_object_vars(f))
