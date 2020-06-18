# coding: utf-8

import datetime

d1 = datetime.datetime(2009, 10, 11)
d2 = datetime.datetime(2009, 10, 13)
print((d2 - d1).days)
print((d2 - d1).seconds)
print((d2 - d1).total_seconds())
