# coding: utf-8

import time


def localtime(secs=None):
    return time.localtime(secs)


if __name__ == '__main__':
    print(localtime())
