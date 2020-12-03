# coding: utf-8

import hashlib


def md5(s, raw_output=False):
    m = hashlib.md5()
    m.update(s)
    if raw_output:
        return m.digest()
    else:
        return m.hexdigest()


if __name__ == '__main__':
    s = 'apple'
    print(md5(s.encode()))
