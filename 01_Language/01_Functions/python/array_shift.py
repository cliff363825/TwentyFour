# coding: utf-8

def array_shift(arr):
    try:
        return arr.pop(0)
    except IndexError:
        return None


if __name__ == '__main__':
    stack = ["orange", "banana", "apple", "raspberry"]
    print(array_shift(stack))

    stack = {"k1": "orange", "k2": "banana", 0: "apple", 1: "raspberry"}
    print(array_shift(stack))
