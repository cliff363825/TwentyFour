# coding: utf-8

import json


def json_decode(s):
    return json.loads(s)


if __name__ == '__main__':
    s = '{"a":1,"b":2,"c":3,"d":4,"e":5}'
    print(json_decode(s))
