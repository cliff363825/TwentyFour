# coding: utf-8

import hashlib


def hash_file(algo, filename):
    with open(filename, mode="rb") as f:
        h = hashlib.new(algo)
        while True:
            data = f.read(8192)
            if not data:
                break
            h.update(data)
        return h.hexdigest()


if __name__ == '__main__':
    with open("test.txt", mode="w") as f:
        f.write("The quick brown fox jumped over the lazy dog.")
    print(hash_file("md5", "test.txt"))
