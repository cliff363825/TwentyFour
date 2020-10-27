# coding: utf-8

import os


def is_file(filename):
    return os.path.isfile(filename)


if __name__ == '__main__':
    print(is_file("test.txt"))
    print(is_file("/usr/bin/"))
