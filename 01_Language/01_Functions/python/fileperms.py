# coding: utf-8
import os


def fileperms(filename):
    return os.stat(filename).st_mode


if __name__ == '__main__':
    print(fileperms("test.txt"))
