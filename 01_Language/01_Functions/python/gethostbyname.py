# coding: utf-8

import socket


def gethostbyname(hostname):
    try:
        return socket.gethostbyname(hostname)
    except socket.gaierror:
        return hostname


if __name__ == '__main__':
    print(gethostbyname("www.onevgo.com"))
    print(gethostbyname("ss.onevgo.com"))
