# coding: utf-8
import types
from array_diff import array_diff


def foo(*args, **kwargs):
    print("Number of arguments: ", len(args))

foo(1, 2, 3)
