# coding: utf-8

import math


def is_nan(val):
    return math.isnan(val)


if __name__ == '__main__':
    print(is_nan(math.nan))
    # print(is_nan(math.acos(3)))
