# coding: utf-8

import functools


def join(glue, pieces):
    return str(glue).join(pieces)


if __name__ == '__main__':
    arr = ['lastname', 'email', 'phone']
    print(join(',', arr))

    print(functools.reduce(lambda x, y: str(x) + ',' + str(y), arr))
