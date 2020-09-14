# coding: utf-8

import datetime
import pytz

if __name__ == '__main__':
    print(repr(datetime.datetime.now().tzinfo))

    tz = pytz.timezone("Europe/London")
    d = datetime.datetime.now(tz)
    print(d.tzinfo)
