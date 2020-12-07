# coding: utf-8

import hashlib


def md5_file(filename):
    with open(filename, mode='rb') as f:
        h = hashlib.md5()
        while True:
            data = f.read(8192)
            if not data:
                break
            h.update(data)
        return h.hexdigest()


if __name__ == '__main__':
    print(md5_file('test.txt'))
