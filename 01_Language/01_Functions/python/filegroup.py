# coding: utf-8

import os


def filegroup(filename):
    return os.stat(filename).st_gid


if __name__ == '__main__':
    print(filegroup("test.txt"))
