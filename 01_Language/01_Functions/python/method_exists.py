# coding: utf-8

def method_exists(obj, method_name):
    return hasattr(obj, method_name) and callable(getattr(obj, method_name))


if __name__ == '__main__':
    import hashlib

    h = hashlib.md5()
    print(method_exists(h, 'update'))
