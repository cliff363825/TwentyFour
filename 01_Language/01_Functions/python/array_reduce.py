# coding: utf-8

import functools


def array_reduce(arr, cb, *args):
    if len(args) > 0:
        return functools.reduce(cb, arr, args[0])
    else:
        return functools.reduce(cb, arr)


if __name__ == '__main__':
    l = [1, 2, 3, 4, 5]
    print(array_reduce(l, lambda x, y: x + y))
    print(array_reduce(l, lambda x, y: x * y, 10))
    print(array_reduce([], lambda x, y: x + y, "No data to reduce"))
