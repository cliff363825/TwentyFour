# coding: utf-8

import datetime


def checkdate(m, d, y):
    y, m, d = map(int, (y, m, d))
    try:
        datetime.date(y, m, d)
        return True
    except ValueError:
        return False


if __name__ == '__main__':
    print(checkdate(12, 31, 2000))
    print(checkdate(2, 29, 2001))
