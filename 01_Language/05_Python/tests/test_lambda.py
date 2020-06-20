# coding: utf-8

from collections import Iterable

l = [1, 2, 3, 4, 5, 6, 7, 8, 9]
r = map(lambda x: x * x, l)
assert isinstance(r, Iterable)
print(list(r))
