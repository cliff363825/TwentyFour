# coding: utf-8

import hashlib


def hash(algo, data, raw_output=False):
    h = hashlib.new(algo)
    h.update(data)
    if raw_output:
        return h.digest()
    else:
        return h.hexdigest()


if __name__ == '__main__':
    print(hash('ripemd160', 'The quick brown fox jumped over the lazy dog.'.encode()))
