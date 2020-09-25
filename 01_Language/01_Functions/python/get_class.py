# coding: utf-8

def get_class(obj):
    return type(obj).__name__


if __name__ == '__main__':
    class bar(object):
        def __init__(self):
            print(get_class(self))


    class foo(bar):
        pass


    foo()
