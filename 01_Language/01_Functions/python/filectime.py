# coding: utf-8

import os
import datetime


def filectime(filename):
    return os.path.getctime(filename)


if __name__ == '__main__':
    print(datetime.datetime.fromtimestamp(filectime("test.txt")))
