# coding: utf-8

import os


def is_writable(filename):
    return os.access(filename, os.W_OK)


if __name__ == '__main__':
    print(is_writable("test.txt"))
