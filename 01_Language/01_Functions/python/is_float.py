# coding: utf-8

def is_float(var):
    return isinstance(var, float)


if __name__ == '__main__':
    print(is_float(27.75))
    print(is_float('abc'))
    print(is_float(23))
    print(is_float(23.5))
    print(is_float(1e7))
    print(is_float(True))
