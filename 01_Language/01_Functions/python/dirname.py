# coding: utf-8

import os


def dirname(path):
    end = len(path) - 1
    while end >= 1 and (path[end] == os.path.sep or path[end] == os.path.altsep):
        end -= 1
    path = path[0:end + 1]
    return os.path.dirname(path) or "."


if __name__ == '__main__':
    print(dirname("/etc/passwd"))
    print(dirname("/etc/"))
    print(dirname("/"))
    print(dirname("//"))
    print(dirname("."))
    print(dirname("C:\\"))
    print(dirname("/usr/local/lib"))
