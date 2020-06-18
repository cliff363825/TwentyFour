# coding: utf-8

def array_merge(*args):
    res = []
    for i in args:
        if not isinstance(i, (list, tuple)):
            res = {}
            break
    if isinstance(res, list):
        for i in args:
            res += i
    else:
        index = 0
        for i in args:
            for k, v in i.items():
                if isinstance(k, int):
                    res[index] = v
                    index += 1
                else:
                    res[k] = v
    return res


if __name__ == '__main__':
    array1 = {"color": "red", 0: 2, 1: 4}
    array2 = {0: "a", 1: "b", "color": "green", "shape": "trapezoid", 2: 4}
    print(array_merge(array1, array2))
    print(array_merge([1, 2, 3], [4, 5, 6], [7, 8, 9]))
