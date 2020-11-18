# coding: utf-8

import json


def json_encode(value):
    return json.dumps(value)


if __name__ == '__main__':
    a = dict((("a", 1), ("b", 2), ("c", 3), ("d", 4), ("e", 5)))
    print(json_encode(a))
