# coding: utf-8

import time


def gmdate(fmt, timestamp=None):
    if timestamp is None:
        t = time.gmtime()
    else:
        t = time.gmtime(timestamp)
    return time.strftime(fmt, t)


if __name__ == '__main__':
    print(gmdate("%Y-%m-%d %H:%M:%S", time.mktime((1998, 1, 1, 0, 0, 0, -1, -1, -1))))

    import date_func

    print(date_func.date("%Y-%m-%d %H:%M:%S"))
    print(gmdate("%Y-%m-%d %H:%M:%S"))
