# coding: utf-8

import re


def preg_grep(pattern, array):
    p = re.compile(pattern)
    return filter(lambda x: p.search(x), array)


if __name__ == '__main__':
    arr = ['1.23', '.45', 'abc']
    print(list(preg_grep(r'^(\d+)?\.\d+$', arr)))
    print(list(preg_grep(r'\d+', arr)))
