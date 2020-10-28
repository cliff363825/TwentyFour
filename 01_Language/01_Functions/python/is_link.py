# coding: utf-8

import os


def is_link(filename):
    return os.path.islink(filename)


if __name__ == '__main__':
    print(is_link("test.txt"))
