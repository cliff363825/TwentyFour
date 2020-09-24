# coding: utf-8

import hkdf
import hashlib


def hash_hkdf(algo, ikm, length=0, info=b'', salt=b''):
    h = getattr(hashlib, algo)
    kdf = hkdf.Hkdf(salt, ikm, h)
    if length == 0:
        length = h().digest_size
    return kdf.expand(info, length)


if __name__ == '__main__':
    input_key = b"abcdef"
    salt = b"abcdef"
    encryption_key = hash_hkdf("sha256", input_key, 32, b"aes-256-encryption", salt)
    authentication_key = hash_hkdf("sha256", input_key, 32, b"sha-256-authentication", salt)
    print(encryption_key)
    print(encryption_key.decode(errors="ignore"))
    print(authentication_key)
    print(authentication_key.decode(errors="ignore"))
