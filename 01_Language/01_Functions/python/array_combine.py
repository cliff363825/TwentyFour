# coding: utf-8

def array_combine(keys, values):
    # dict is unordered
    return dict(zip(keys, values))


if __name__ == '__main__':
    a = ['green', 'red', 'yellow']
    b = ['avocado', 'apple', 'banana']
    print(array_combine(a, b))
