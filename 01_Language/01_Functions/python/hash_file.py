# coding: utf-8

import hashlib


def hash_file(algo, filename):
    h = hashlib.new(algo)
    with open(filename, mode="rb") as f:
        h.update(f.read())
    return h.hexdigest()


if __name__ == '__main__':
    with open("test.txt", mode="w") as f:
        f.write("The quick brown fox jumped over the lazy dog.")
    print(hash_file("md5", "test.txt"))
