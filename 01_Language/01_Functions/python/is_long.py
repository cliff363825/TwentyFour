# coding: utf-8

def is_long(var):
    return isinstance(var, int)


if __name__ == '__main__':
    print(is_long(23))
    print(is_long("23"))
    print(is_long(23.5))
    print(is_long("23.5"))
    print(is_long(None))
    print(is_long(True))
    print(is_long(False))