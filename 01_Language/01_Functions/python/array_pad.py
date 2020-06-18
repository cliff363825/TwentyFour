# coding: utf-8

def array_pad(l, size, value):
    if size >= 0:
        return l + [value] * (size - len(l))
    else:
        return [value] * (size * -1 - len(l)) + l


if __name__ == '__main__':
    l = [12, 10, 9]
    print(array_pad(l, 5, 0))
    print(array_pad(l, -7, -1))
    print(array_pad(l, 2, 999))
