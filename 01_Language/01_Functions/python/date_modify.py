# coding: utf-8

import datetime

if __name__ == '__main__':
    d = datetime.datetime(2006, 12, 12)
    d = d + datetime.timedelta(days=1)
    print(d.strftime("%Y-%m-%d"))
