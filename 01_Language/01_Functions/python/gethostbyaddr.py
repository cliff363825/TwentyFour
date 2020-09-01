# coding: utf-8

import socket


def gethostbyaddr(ip_address):
    try:
        return socket.gethostbyaddr(ip_address)[0]
    except socket.herror:
        return ip_address


if __name__ == '__main__':
    print(gethostbyaddr("193.112.23.137"))
    # print(gethostbyaddr("127.0.0.1"))
