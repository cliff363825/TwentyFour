# coding: utf-8

def array_diff(l, *args):
    res = []
    for i in l:
        for k, v in enumerate(args):
            if i in v:
                break
            if k == len(args) - 1:
                res.append(i)
    return res


def array_diff1(arr1, *args):
    return set(arr1).difference(*args)


if __name__ == '__main__':
    array1 = ["green", "red", "blue", "red"]
    array2 = ["green", "yellow", "red"]
    print(array_diff(array1, array2))
    print(array_diff1(array1, array2))
