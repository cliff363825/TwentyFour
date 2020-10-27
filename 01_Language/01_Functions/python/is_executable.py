# coding: utf-8

import os


def is_executable(filename):
    return os.access(filename, os.X_OK)


if __name__ == '__main__':
    print(is_executable("test.sh"))
