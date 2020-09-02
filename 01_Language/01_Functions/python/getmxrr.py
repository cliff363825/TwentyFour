# coding: utf-8

import dns.resolver


def getmxrr(hostname):
    return [x.to_text() for x in dns.resolver.resolve(hostname, 'MX')]


if __name__ == '__main__':
    print(getmxrr("exmail.qq.com"))
