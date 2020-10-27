# coding: utf-8

def is_double(var):
    return isinstance(var, float)


if __name__ == '__main__':
    print(is_double(27.75))
    print(is_double('abc'))
    print(is_double(23))
    print(is_double(23.5))
    print(is_double(1e7))
    print(is_double(True))
