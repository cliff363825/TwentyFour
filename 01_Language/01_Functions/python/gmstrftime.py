# coding: utf-8

import time


def gmstrftime(fmt, timestamp):
    return time.strftime(fmt, time.gmtime(timestamp))


if __name__ == '__main__':
    print(gmstrftime("%Y-%m-%d %H:%M:%S", time.mktime((1998, 12, 31, 20, 0, 0, -1, -1, -1))))
