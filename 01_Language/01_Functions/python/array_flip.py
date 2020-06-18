# coding: utf-8

def array_flip(arr):
    if isinstance(arr, dict):
        return dict((v, k) for k, v in arr.items())
    else:
        return dict((v, k) for k, v in enumerate(arr))


if __name__ == '__main__':
    input = ["oranges", "apples", "pears"]
    print(array_flip(input))

    input2 = {'a': 1, 'b': 1, 'c': 2}
    print(array_flip(input2))
