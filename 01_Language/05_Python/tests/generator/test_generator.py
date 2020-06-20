# coding: utf-8

l = [x * x for x in range(10)]
print(l)

g = (x * x for x in range(10))
print(g)

# print(next(g)) # 0
# print(next(g)) # 1
# print(next(g)) # 4
# print(next(g)) # 9
# print(next(g)) # 16
# print(next(g)) # 25
# print(next(g)) # 36
# print(next(g)) # 49
# print(next(g)) # 64
# print(next(g)) # 81
# print(next(g)) # raise StopIteration

for n in g:
    print(n)


def fib(max):
    n, a, b = 0, 0, 1
    while n < max:
        print(b)
        a, b = b, a + b
        n = n + 1
    return 'done'


print(fib(6))


def fib2(max):
    n, a, b = 0, 0, 1
    while n < max:
        yield b
        a, b = b, a + b
        n = n + 1
    return 'done'


for n in fib2(6):
    print(n)

g = fib2(6)
while True:
    try:
        x = next(g)
    except StopIteration as e:
        print('Generator return value:', e.value)
        break
    else:
        print(x)
