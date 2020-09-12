# coding: utf-8

import time


def gettimeofday(return_float=False):
    t = time.time()
    if return_float:
        return t
    lt = time.localtime(t)
    sec, usec = str(t).split('.')
    return {
        "sec": int(sec),
        "usec": int(usec),
        "minuteswest": (time.timezone if lt.tm_isdst == 0 else time.altzone) / 60,
        "dsttime": lt.tm_isdst
    }


if __name__ == '__main__':
    print(gettimeofday())
    print(gettimeofday(True))
