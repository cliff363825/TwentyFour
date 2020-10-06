# coding: utf-8

import re


def hexdec(hex_string):
    pattern = re.compile(r'[^0-9A-Fa-f]')
    hex_string = pattern.sub("", hex_string)
    return int(hex_string, 16)


if __name__ == '__main__':
    print(hexdec("See"))
    print(hexdec("ee"))
    print(hexdec("that"))
    print(hexdec("a0"))
