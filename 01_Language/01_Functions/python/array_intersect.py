# coding: utf-8

import functools


def array_intersect(arr, arr2, *args):
    return functools.reduce(lambda x, y: x.intersection(y), [set(arr), arr2, *args])


if __name__ == '__main__':
    array1 = ["green", "red", "blue"]
    array2 = ["green", "yellow", "red"]
    print(array_intersect(array1, array2))
