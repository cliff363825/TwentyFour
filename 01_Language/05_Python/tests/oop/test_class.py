# coding: utf-8
import collections


class Fib(object):
    def __init__(self):
        self.a, self.b = 0, 1

    def __iter__(self):
        print('call __iter__')
        return self

    def __next__(self):
        self.a, self.b = self.b, self.a + self.b
        if self.a > 100000:
            raise StopIteration()
        else:
            return self.a

    def __getitem__(self, item):
        a, b = 1, 1
        if isinstance(item, slice):
            start = 0 if item.start is None or item.start < 0 else item.start
            stop = 0 if item.stop is None or item.stop < 0 else item.stop
            step = 1 if item.step is None or item.step < 0 else item.step
            l = []
            for i in range(start, stop, step):
                l.append(a)
                a, b = b, a + b
            return l
        if isinstance(item, int):
            for i in range(item):
                a, b = b, a + b
            return a
        raise ValueError()


assert isinstance(Fib(), collections.Iterator)
for i in Fib():
    print(i)

print(Fib()[0])
print(Fib()[1])
print(Fib()[2])
print(Fib()[10])
print(Fib()[100])

print(Fib()[0:20])
