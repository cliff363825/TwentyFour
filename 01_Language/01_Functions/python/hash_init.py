# coding: utf-8

import hashlib


def hash_init(algo):
    return hashlib.new(algo)


if __name__ == '__main__':
    h = hash_init("md5")
    h.update(b"The quick brown fox ")
    h.update(b"jumped over the lazy dog.")
    print(h.hexdigest())
