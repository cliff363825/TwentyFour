# coding: utf-8

import socket


def getservbyname(service, protocol):
    return socket.getservbyname(service, protocol)


if __name__ == '__main__':
    services = ('http', 'ftp', 'ssh', 'telnet', 'imap',
                'smtp', 'nicname', 'gopher', 'finger', 'pop3', 'www')
    for s in services:
        print(s, getservbyname(s, 'tcp'))
