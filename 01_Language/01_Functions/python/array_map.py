# coding: utf-8

def array_map(cb, *args):
    return list(map(cb, *args))


if __name__ == '__main__':
    a = [1, 2, 3, 4, 5]
    b = array_map(lambda x: x * x * x, a)
    print(b)

    a = [1, 2, 3, 4, 5]
    b = ['uno', 'dos', 'tres', 'cuatro', 'cinco']
    print(array_map(lambda x, y: {x: y}, a, b))

    a = [1, 2, 3, 4, 5]
    b = ['one', 'two', 'three', 'four', 'five']
    c = ['uno', 'dos', 'tres', 'cuatro', 'cinco']
    print(array_map(lambda x, y, z: [x, y, z], a, b, c))
    print(list(zip(a, b, c)))
