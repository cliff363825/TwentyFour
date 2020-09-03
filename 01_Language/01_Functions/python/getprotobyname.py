# coding: utf-8

import socket


def getprotobyname(name):
    return socket.getprotobyname(name)


if __name__ == '__main__':
    print(getprotobyname("tcp"))
