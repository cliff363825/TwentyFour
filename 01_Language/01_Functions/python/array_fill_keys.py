# coding: utf-8

def array_fill_keys(keys, value):
    return dict.fromkeys(keys, value)


if __name__ == '__main__':
    keys = ['foo', 5, 10, 'bar']
    print(array_fill_keys(keys, 'banana'))
