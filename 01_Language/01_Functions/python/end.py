# coding: utf-8

def end(arr):
    res = None
    if isinstance(arr, dict):
        for _, v in arr.items():
            res = v
    else:
        res = arr[-1]
    return res


if __name__ == '__main__':
    fruits = ['apple', 'banana', 'cranberry']
    print(end(fruits))
