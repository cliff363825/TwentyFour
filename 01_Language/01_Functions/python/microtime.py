# coding: utf-8

import time
import math

def microtime(get_as_float=False):
    now = time.time()
    return now if get_as_float else '%f %d' % math.modf(now)

if __name__ == '__main__':
    print(microtime())
    print(microtime(True))