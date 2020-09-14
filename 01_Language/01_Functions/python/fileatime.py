# coding: utf-8

import os
import time


def fileatime(filename):
    return os.path.getatime(filename)


if __name__ == '__main__':
    print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime(fileatime("test.txt"))))
