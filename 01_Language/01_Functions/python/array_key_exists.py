# coding: utf-8

def array_key_exists(key, d):
    return key in d


if __name__ == '__main__':
    search_array = {'first': 1, 'second': 4, '': False}
    print(array_key_exists('first', search_array))
