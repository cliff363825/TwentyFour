# coding: utf-8

from collections import OrderedDict


# python 3.6+
def arsort(arr):
    return {k: v for k, v in sorted(arr.items(), key=lambda x: x[1], reverse=True)}


def arsort1(arr):
    return OrderedDict(sorted(arr.items(), key=lambda x: x[1], reverse=True))


if __name__ == '__main__':
    fruits = {"d": "lemon", "a": "orange", "b": "banana", "c": "apple"}
    # fruits1 = arsort(fruits)
    fruits1 = arsort1(fruits)
    print(fruits1)
