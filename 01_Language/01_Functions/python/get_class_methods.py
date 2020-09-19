# coding: utf-8

def get_class_methods(class_name):
    return [method for method in vars(class_name).keys() if callable(getattr(class_name, method))]


if __name__ == '__main__':
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

    print(get_class_methods(myclass))

    import hashlib

    print(get_class_methods(hashlib))
