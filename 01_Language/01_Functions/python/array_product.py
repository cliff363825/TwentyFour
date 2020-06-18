# coding: utf-8

import functools


def array_product(arr):
    if len(arr) == 0:
        return 1
    return functools.reduce(lambda x, y: x * y, arr)


if __name__ == '__main__':
    l = [2, 4, 6, 8]
    print(array_product(l))
    print(array_product([]))
