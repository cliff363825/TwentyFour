# coding: utf-8

import socket


def gethostbynamel(hostname):
    try:
        return socket.gethostbyname_ex(hostname)[2]
    except socket.gaierror:
        return []


if __name__ == '__main__':
    print(gethostbynamel("www.onevgo.com"))
    print(gethostbynamel("ss.onevgo.com"))
