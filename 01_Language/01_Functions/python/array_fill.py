# coding: utf-8

def array_fill(start, num, val):
    return [None if i < start else val for i in range(start + num)]


if __name__ == '__main__':
    print(array_fill(5, 6, 'banana'))
    print(['banana'] * 6)
