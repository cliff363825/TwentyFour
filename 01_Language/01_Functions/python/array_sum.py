# coding: utf-8

def array_sum(arr):
    if isinstance(arr, dict):
        return sum(arr.values())
    return sum(arr)


if __name__ == '__main__':
    b = {"a": 1.2, "b": 2.3, "c": 3.4}
    print(array_sum(b))
