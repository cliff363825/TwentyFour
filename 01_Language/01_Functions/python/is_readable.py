# coding: utf-8

import os


def is_readable(filename):
    return os.access(filename, os.R_OK)


if __name__ == '__main__':
    print(is_readable("test.txt"))
