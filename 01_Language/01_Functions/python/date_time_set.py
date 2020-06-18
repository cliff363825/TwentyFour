# coding: utf-8

import datetime

d = datetime.datetime.strptime("2001-01-01", "%Y-%m-%d")
d = d.replace(hour=14, minute=55)
print(d)
