# coding: utf-8

from collections import OrderedDict


def krsort(array):
    return OrderedDict(sorted(array.items(), reverse=True))


if __name__ == '__main__':
    fruits = {
        "d": "lemon",
        "a": "orange",
        "b": "banana",
        "c": "apple",
    }
    print(krsort(fruits))
