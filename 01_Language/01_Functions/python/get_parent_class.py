# coding: utf-8

def get_parent_class(cls):
    if not isinstance(cls, type):
        cls = type(cls)
    return cls.__bases__


if __name__ == '__main__':
    class dad(object):
        pass


    class child(dad):
        def __new__(cls, *args, **kwargs):
            print(cls, args, kwargs)
            print("I'm ", get_parent_class(cls), "'s son")
            return object.__new__(cls)


    class child2(dad):
        def __new__(cls, *args, **kwargs):
            print(cls, args, kwargs)
            o = object.__new__(cls)
            print("I'm ", get_parent_class(o), "'s son too")
            return o


    f = child()
    b = child2()
