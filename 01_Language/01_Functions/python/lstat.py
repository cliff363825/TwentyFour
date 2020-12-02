# coding: utf-8

import os


def lstat(filename):
    return os.lstat(filename)


if __name__ == '__main__':
    import link

    link.link('test.txt', 'test')
    print(lstat('test'))
