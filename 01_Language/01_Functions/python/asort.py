# coding: utf-8

from collections import OrderedDict


# python 3.6+
def asort(arr):
    return {k: v for k, v in sorted(arr.items(), key=lambda x: x[1])}


def arsort1(arr):
    return OrderedDict(sorted(arr.items(), key=lambda x: x[1]))


if __name__ == '__main__':
    fruits = {"d": "lemon", "a": "orange", "b": "banana", "c": "apple"}
    print(asort(fruits))
