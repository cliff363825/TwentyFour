# coding: utf-8

import asyncio


def reader():
    """A genenator that fakes a read from a file, socket, ect"""
    for i in range(4):
        yield '<< %s' % i


class Reader(object):
    def __init__(self, count):
        self._count = count
        self._i = 0

    def __iter__(self):
        print('call __iter__')
        return self

    def __next__(self):
        if self._i < self._count:
            s = '<< %s' % self._i
            self._i += 1
            return s
        else:
            raise StopIteration()


def reader_wrapper(g):
    # Manually iterate over data produced by reader
    for v in g:
        yield v


def reader_wrapper2(g):
    yield from g


wrap = reader_wrapper(reader())
for i in wrap:
    print(i)

wrap = reader_wrapper2(reader())
for i in wrap:
    print(i)

wrap = reader_wrapper2(Reader(4))
for i in wrap:
    print(i)
