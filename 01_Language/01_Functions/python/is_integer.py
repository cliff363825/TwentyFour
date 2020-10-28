# coding: utf-8

def is_integer(var):
    return isinstance(var, int)


if __name__ == '__main__':
    print(is_integer(23))
    print(is_integer("23"))
    print(is_integer(23.5))
    print(is_integer("23.5"))
    print(is_integer(None))
    print(is_integer(True))
    print(is_integer(False))
