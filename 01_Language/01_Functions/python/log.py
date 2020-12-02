# coding: utf-8

import math


def log(arg, base=None):
    if base is None:
        return math.log(arg)
    return math.log(arg, base)


if __name__ == '__main__':
    print(log(1))
    print(log(2, 2))
