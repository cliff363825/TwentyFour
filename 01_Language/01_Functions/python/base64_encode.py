# coding: utf-8

import sys


def base64_encode(data):
    if sys.version_info.major == 2:
        # python = 2.x:
        return data.encode('base64')
    elif sys.version_info <= (3, 1):
        # python = 3.1:
        import base64
        return base64.encodestring(data)
    else:
        # python > 3.1
        import base64
        return base64.b64encode(data.encode("utf-8")).decode("utf-8")


if __name__ == '__main__':
    print(base64_encode("This is an encoded string"))
