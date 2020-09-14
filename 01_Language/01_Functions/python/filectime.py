# coding: utf-8

import os
import time


def filectime(filename):
    return os.path.getctime(filename)


if __name__ == '__main__':
    print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime(filectime("test.txt"))))
