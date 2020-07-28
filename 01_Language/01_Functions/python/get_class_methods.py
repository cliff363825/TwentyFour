# coding: utf-8

class myclass(object):
    def __init__(self):
        pass

    # method 1
    def _myfunc1(self):
        return True

    def myfunc2(self):
        return True

# print(myclass.__dict__)
# print(vars(myclass))
def get_class_methods(cls):
    return [method for method in cls.__dict__.keys() if callable(getattr(cls, method))]

if __name__ == '__main__':
    print(get_class_methods(myclass))
