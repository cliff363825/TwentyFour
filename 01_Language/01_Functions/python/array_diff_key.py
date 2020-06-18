# coding: utf-8

def array_diff_key(d, *args):
    res = {}
    for k, v in d.items():
        for n, arg in enumerate(args):
            if k in arg.keys():
                break
            if n == len(args) - 1:
                res[k] = v
    return res


if __name__ == '__main__':
    array1 = {'blue': 1, 'red': 2, 'green': 3, 'purple': 4}
    array2 = {'green': 5, 'yellow': 7, 'cyan': 8}
    print(array_diff_key(array1, array2))
