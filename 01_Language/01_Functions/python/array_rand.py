# coding: utf-8

import random


def array_rand(arr, num=1):
    if isinstance(arr, dict):
        keys = arr.keys()
    else:
        keys = range(len(arr))
    if num == 1:
        return random.choice(keys)
    else:
        return random.sample(keys, num)


if __name__ == '__main__':
    l = ["Neo", "Morpheus", "Trinity", "Cypher", "Tank"]
    print(array_rand(l, 2))
    print(array_rand(l))

    d = {"k1": "Neo", "k2": "Morpheus", "k3": "Trinity", "k4": "Cypher", "k5": "Tank"}
    print(array_rand(d, 2))
