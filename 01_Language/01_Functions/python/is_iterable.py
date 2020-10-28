# coding: utf-8

def is_iterable(value):
    try:
        iter(value)
        return True
    except TypeError:
        return False


if __name__ == '__main__':
    def f():
        yield 1


    print(is_iterable([1, 2, 3]))
    print(is_iterable(iter([1, 2, 3])))
    print(is_iterable(f()))
    print(is_iterable(1))
    print(is_iterable(object()))
