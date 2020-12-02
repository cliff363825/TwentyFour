# coding: utf-8

from socket import inet_ntoa
from struct import pack


def long2ip(proper_address):
    return inet_ntoa(pack("!L", proper_address))


if __name__ == '__main__':
    print(long2ip(3245348745))
