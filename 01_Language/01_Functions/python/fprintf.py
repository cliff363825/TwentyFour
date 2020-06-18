# coding: utf-8

import datetime

f = open("test.txt", mode="w")

d = datetime.datetime.now()
year, month, day = d.year, d.month, d.day
# f.write("%04d-%02d-%02d" % (year, month, day))
print("%04d-%02d-%02d" % (year, month, day), file=f)
f.close()
