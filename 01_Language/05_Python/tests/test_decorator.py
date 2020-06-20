# coding: utf-8

import functools
from datetime import datetime, timezone, timedelta


def log(func):
    @functools.wraps(func)
    def wrapper(*args, **kwargs):
        print('call %s():' % func.__name__)
        return func(*args, **kwargs)

    return wrapper


def log2(text):
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args, **kwargs):
            print('%s %s' % (text, func.__name__))
            return func(*args, **kwargs)

        return wrapper

    return decorator


@log
def now():
    print(datetime.now(timezone(timedelta(hours=8))))


@log2('hello world')
def now2():
    print(datetime.now(timezone(timedelta(hours=8))))


now()
now2()
