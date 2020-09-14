# coding: utf-8

import datetime

if __name__ == '__main__':
    d = datetime.datetime(2000, 1, 20)
    d = d - datetime.timedelta(days=10)
    print(d.strftime("%Y-%m-%d"))
