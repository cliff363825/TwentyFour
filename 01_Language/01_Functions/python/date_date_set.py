# coding: utf-8

import datetime

d = datetime.datetime.now()
d = d.replace(2001, 2, 3)
print(d.strftime("%Y-%m-%d"))
