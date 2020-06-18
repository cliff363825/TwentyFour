# coding: utf-8

import os
import datetime


def fileatime(filename):
    return os.path.getatime(filename)


if __name__ == '__main__':
    print(datetime.datetime.fromtimestamp(fileatime("test.txt")))
