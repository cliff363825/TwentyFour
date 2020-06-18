# coding: utf-8

import os, time


def date_default_timezone_set(tz):
    os.environ['TZ'] = tz
    time.tzset()


print(time.strftime("%Y-%m-%d %H:%M:%S %Z %z"))
date_default_timezone_set("America/Los_Angeles")
print(time.strftime("%Y-%m-%d %H:%M:%S %Z %z"))
