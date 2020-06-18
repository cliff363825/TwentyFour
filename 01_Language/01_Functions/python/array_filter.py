# coding: utf-8

def array_filter(arr, cb=None):
    return filter(cb, arr)


if __name__ == '__main__':
    array1 = {'a': 1, 'b': 2, 'c': 3, 'd': 4, 'e': 5}
    array2 = [6, 7, 8, 9, 10, 11, 12]


    def odd(e):
        _, v = e
        return v % 2 == 1


    def even(e):
        return e % 2 == 0


    print(list(array_filter(array1.items(), odd)))
    print(dict(array_filter(array1.items(), odd)))
    print(list(array_filter(array2, even)))

    entry = ['foo', False, -1, None, '', '0', 0]
    print(list(array_filter(entry)))
