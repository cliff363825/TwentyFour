# coding: utf-8

def gen():
    print('yield 1')
    x = yield 1
    print('return', x)
    return x


def delegate_gen():
    g = gen()
    print('g', g)
    x = yield from g
    print('x', x)
    return x


g = delegate_gen()
r = g.send(None)
print('r', r)
for i in [11, 22, 33]:
    print(g.send(i))

'''
g <generator object gen at 0x000001AAF76DBEB8>
yield 1
r 1
return 1
x 1
Traceback (most recent call last):
  File "D:/phpStudy/WWW/git/my_python/tests/generator/test_yield_from_coroutine.py", line 20, in <module>
    print(g.send(i))
StopIteration: 1
'''
