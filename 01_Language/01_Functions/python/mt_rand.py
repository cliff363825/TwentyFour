# coding: utf-8

import random


def mt_rand(min_val=0, max_val=2147483647):
    return random.randint(min_val, max_val)


if __name__ == '__main__':
    print(mt_rand())
    print(mt_rand(5, 15))
    print(mt_rand(1, 2))
