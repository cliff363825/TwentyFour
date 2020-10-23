# coding: utf-8

def is_bool(var):
    return isinstance(var, bool)


if __name__ == '__main__':
    a = False
    b = 0
    print(is_bool(a))
    print(is_bool(b))
