# coding: utf-8

import socket


def ip2long(ip_address):
    try:
        result = 0
        ip_bytes = socket.inet_aton(ip_address)
        for b in ip_bytes:
            result = (result << 8) | (b & 0XFF)
        return result
    except OSError:
        return False


if __name__ == '__main__':
    from gethostbyname import gethostbyname

    print(ip2long(gethostbyname("www.onevgo.com")))
    print(ip2long("193.112.23.137"))
    print(ip2long("999.999.999.999"))
