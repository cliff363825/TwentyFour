# coding: utf-8

import os


def is_dir(filename):
    return os.path.isdir(filename)


if __name__ == '__main__':
    print(is_dir("test.txt"))
    print(is_dir("test1/test2"))
    print(is_dir(".."))
