# coding: utf-8

def array_values(arr):
    if isinstance(arr, dict):
        return arr.values()
    return arr[:]


if __name__ == '__main__':
    array = {"size": "XL", "color": "gold"}
    print(array_values(array))
