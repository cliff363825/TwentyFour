# coding: utf-8

def g():
    n = 0
    r = yield
    while r != 'exit':
        n += 1
        r = yield n
    return 'over'


def f(g):
    g.send(None)
    while True:
        s = input('press [exit] to stop: ')
        try:
            n = g.send(s)
            print('natural number：', n)
        except StopIteration as e:
            print('return value:', e.value)
            break


f(g())
