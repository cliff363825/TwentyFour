# coding: utf-8
import os


def filemtime(filename):
    return os.path.getmtime(filename)


if __name__ == '__main__':
    print(filemtime("test.txt"))
