# coding: utf-8

import datetime


def date_isodate(year, week, day=1):
    if day < 1 or day > 7:
        raise ValueError("1 <= day <= 7")
    first = datetime.datetime(year, 1, 1)
    days = day - first.isocalendar()[2]
    if first.isocalendar()[1] != 1:
        days += 7
    return first + datetime.timedelta(days=days, weeks=week - 1)


if __name__ == '__main__':
    print(date_isodate(2008, 2))
    print(date_isodate(2008, 2, 7))

    for i in range(1970, 2021):
        print(i, datetime.date(i, 1, 1).isocalendar())
