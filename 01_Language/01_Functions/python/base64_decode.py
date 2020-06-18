# coding: utf-8

import sys


def base64_decode(data):
    encoded_data = data + "==="
    if sys.version_info.major == 2:
        # python = 2.x:
        return encoded_data.decode('base64')
    elif sys.version_info <= (3, 1):
        # python = 3.1:
        import base64
        return base64.decodestring(encoded_data)
    else:
        # python > 3.1
        import base64
        return base64.b64decode(encoded_data).decode("utf-8")


if __name__ == '__main__':
    print(base64_decode("VGhpcyBpcyBhbiBlbmNvZGVkIHN0cmluZw=="))
