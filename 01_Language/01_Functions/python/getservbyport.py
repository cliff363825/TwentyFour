# coding: utf-8

import socket


def getservbyport(port, protocol):
    return socket.getservbyport(port, protocol)


if __name__ == '__main__':
    print(getservbyport(80, "tcp"))
