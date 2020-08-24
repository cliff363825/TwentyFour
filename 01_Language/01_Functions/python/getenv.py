# coding: utf-8

import os


def getenv(varname=None):
    if varname is None:
        return os.environ

    return os.environ[varname]


if __name__ == '__main__':
    print(getenv())
    print(getenv("PATH"))
