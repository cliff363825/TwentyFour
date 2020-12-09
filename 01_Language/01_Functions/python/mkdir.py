# coding: utf-8

import os


def mkdir(pathname, mode=0o777, recursive=False):
    try:
        if recursive:
            os.makedirs(pathname, mode)
        else:
            os.mkdir(pathname, mode)
        return True
    except:
        return False


if __name__ == '__main__':
    print(mkdir('./depth1/depth2/depth3/', 0o777, True))
