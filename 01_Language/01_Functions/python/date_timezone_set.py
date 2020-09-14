# coding: utf-8

import pytz
import datetime

if __name__ == '__main__':
    d = datetime.datetime.strptime("2000-01-01", "%Y-%m-%d")
    print(d.strftime("%Y-%m-%d %H:%M:%S %z"))
    d = d.replace(tzinfo=pytz.timezone("Pacific/Nauru"))
    print(d.strftime("%Y-%m-%d %H:%M:%S %z"))
    d = d.astimezone(pytz.timezone("Pacific/Chatham"))
    print(d.strftime("%Y-%m-%d %H:%M:%S %z"))
