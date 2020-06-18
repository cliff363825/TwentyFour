# coding: utf-8
import os


def fileowner(filename):
    return os.stat(filename).st_uid


if __name__ == '__main__':
    print(fileowner("test.txt"))
