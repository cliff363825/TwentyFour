# coding: utf-8

import datetime


def mktime(hour, minute, second, month, day, year):
    return datetime.datetime(year, month, day, hour, minute, second).timestamp()


if __name__ == '__main__':
    import os

    os.environ['TZ'] = 'UTC'
    print(mktime(0, 0, 0, 7, 1, 2000))
