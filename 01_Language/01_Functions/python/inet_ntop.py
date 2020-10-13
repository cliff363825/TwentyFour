# coding: utf-8

import socket

def inet_ntop(in_addr):
    if len(in_addr) == 4:
        return socket.inet_ntop(socket.AF_INET, in_addr)
    elif len(in_addr) == 16:
        return socket.inet_ntop(socket.AF_INET6, in_addr)
    else:
        return False

if __name__ == '__main__':
    print(inet_ntop(b"\x7F\x00\x00\x01"))
    print(inet_ntop(b"\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\1"))
    print(inet_ntop(b"\xff\0\0\0\0\0\0\0\0\0\0\0\0\0\0\1"))