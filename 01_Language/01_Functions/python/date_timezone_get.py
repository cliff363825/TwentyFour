# coding: utf-8

import datetime
import pytz

tz = pytz.timezone("Europe/London")
d = datetime.datetime.now(tz)
print(d.tzinfo)
