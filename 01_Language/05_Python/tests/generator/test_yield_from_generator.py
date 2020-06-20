# coding: utf-8

def gen():
    print('yield 1...')
    yield 1
    print('return 2...')
    return 2


def delegate_gen():
    g = gen()
    print('g', g)
    r = yield from g
    print('r', r)
    return r


for i in delegate_gen():
    print(i)

'''
g <generator object gen at 0x00000263D997BEB8>
yield 1...
1
return 2...
r 2
'''
