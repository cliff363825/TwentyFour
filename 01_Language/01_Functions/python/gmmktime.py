# coding: utf-8

import calendar


def gmmktime(hour, minute, second, month, day, year):
    return calendar.timegm((year, month, day, hour, minute, second))


if __name__ == '__main__':
    print(gmmktime(0, 0, 0, 7, 1, 2000))
