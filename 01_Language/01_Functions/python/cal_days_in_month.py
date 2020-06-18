# coding: utf-8

import calendar


def cal_days_in_month(month, year):
    return calendar.monthrange(year, month)[1]


if __name__ == '__main__':
    print(cal_days_in_month(8, 2003))
