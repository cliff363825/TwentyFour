# coding: utf-8

from collections import OrderedDict


def ksort(array):
    return OrderedDict(sorted(array.items()))
