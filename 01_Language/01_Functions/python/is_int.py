# coding: utf-8

def is_int(var):
    return isinstance(var, int)


if __name__ == '__main__':
    print(is_int(23))
    print(is_int("23"))
    print(is_int(23.5))
    print(is_int("23.5"))
    print(is_int(None))
    print(is_int(True))
    print(is_int(False))
