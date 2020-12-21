# coding: utf-8

import urllib.parse


def parse_str(s):
    return urllib.parse.parse_qs(s)


if __name__ == '__main__':
    s = 'first=value&arr[]=foo+bar&arr[]=baz'
    print(parse_str(s))
