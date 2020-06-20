# coding: utf-8

from collections import Iterable, Iterator

x = range(100)
assert isinstance(x, Iterable)


# 所有自然数
def natural_number():
    n = 1
    while True:
        yield n
        n += 1


# 所有奇数
def odd_number():
    n = 1
    while True:
        yield n
        n += 2


# 所有偶数
def even_number():
    n = 0
    while True:
        yield n
        n += 2


for n in even_number():
    if n < 100:
        print(n)
    else:
        break

with open(__file__, 'r', encoding='utf-8') as f:
    assert isinstance(f, Iterable)
    for line in f:
        print(line.rstrip())
