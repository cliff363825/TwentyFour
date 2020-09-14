# coding: utf-8

import datetime
import time

if __name__ == '__main__':
    import os

    os.environ['TZ'] = 'Europe/London'

    print(time.strftime("%Z %z", time.localtime()))

    print(repr(datetime.datetime.now().astimezone().tzinfo))
