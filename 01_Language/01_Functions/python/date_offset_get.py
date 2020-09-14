# coding: utf-8

import pytz
import datetime

if __name__ == '__main__':
    tz = pytz.timezone("America/New_York")
    winter = datetime.datetime.strptime("2010-12-21", "%Y-%m-%d")
    summer = datetime.datetime.strptime("2008-06-21", "%Y-%m-%d")
    print(tz.utcoffset(winter).total_seconds())
    print(tz.utcoffset(summer).total_seconds())
