# coding: utf-8

def array_pop(arr):
    try:
        return arr.pop()
    except IndexError:
        return None


if __name__ == '__main__':
    stack = ["orange", "banana", "apple", "raspberry"]
    print(array_pop(stack))
    print(array_pop(stack))
    print(array_pop(stack))
    print(array_pop(stack))
    print(array_pop(stack))
