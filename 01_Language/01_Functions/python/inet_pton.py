# coding: utf-8

import socket


def inet_pton(address):
    if "." in address:
        return socket.inet_pton(socket.AF_INET, address)
    elif ":" in address:
        return socket.inet_pton(socket.AF_INET6, address)
    else:
        return False


if __name__ == '__main__':
    print(repr(inet_pton("127.0.0.1")))
    print(repr(inet_pton("::1")))
