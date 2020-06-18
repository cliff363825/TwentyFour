# coding: utf-8
import os


def fileinode(filename):
    return os.stat(filename).st_ino


if __name__ == '__main__':
    print(fileinode("test.txt"))
