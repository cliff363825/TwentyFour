# coding: utf-8

import datetime

d = datetime.datetime(2000, 1, 1)
d = d + datetime.timedelta(days=10)
print(d.strftime("%Y-%m-%d"))
