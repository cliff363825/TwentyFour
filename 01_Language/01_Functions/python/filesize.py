# coding: utf-8
import os


def filesize(filename):
    return os.path.getsize(filename)


if __name__ == '__main__':
    print(filesize("test.txt"))
