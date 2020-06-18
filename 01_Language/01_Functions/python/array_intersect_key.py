# coding: utf-8

def array_intersect_key(d, *args):
    keys = d.keys()
    for arg in args:
        keys &= arg.keys()
    return dict((k, d[k]) for k in keys)


if __name__ == '__main__':
    array1 = {'blue': 1, 'red': 2, 'green': 3, 'purple': 4}
    array2 = {'green': 5, 'blue': 6, 'yellow': 7, 'cyan': 8}
    print(array_intersect_key(array1, array2))
