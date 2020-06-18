# coding: utf-8

import binascii


def bin2hex(data):
    return binascii.hexlify(data)


if __name__ == '__main__':
    print(bin2hex("中国".encode()))
