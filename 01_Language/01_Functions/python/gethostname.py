# coding: utf-8

import socket
import os


def gethostname():
    # return os.uname()[1]
    return socket.gethostname()


if __name__ == '__main__':
    print(gethostname())
