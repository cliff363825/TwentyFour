# coding: utf-8

import hashlib


def hash_final(context, raw_output=False):
    if raw_output:
        return context.digest()
    else:
        return context.hexdigest()


if __name__ == '__main__':
   h = hashlib.sha1()
   h.update(b"The quick brown fox jumped over the lazy dog.")
   print(hash_final(h))