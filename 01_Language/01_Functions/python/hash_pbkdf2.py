# coding: utf-8

import hashlib


def hash_pbkdf2(algo, password, salt, iterations, length=None):
    return hashlib.pbkdf2_hmac(algo, password, salt, iterations, length)


if __name__ == '__main__':
    password = b"password"
    iterations = 1000
    salt = b"abcdef"


    def hexstr(data, lowercase=True):
        if lowercase:
            chars = "0123456789abcdef"
        else:
            chars = "0123456789ABCDEF"
        res = []
        for b in data:
            res.append(chars[(b & 0xF0) >> 4])
            res.append(chars[(b & 0x0F)])
        return "".join(res)


    # 91db26ffce86edda06e752019495a1d2b9f32e4c5e8570d990a2c95b1dff0513
    # 91db26ffce86edda06e752019495a1d2b9f32e4c5e8570d990a2c95b1dff0513
    print(hexstr(hash_pbkdf2("sha256", password, salt, iterations)))
    print(hexstr(hash_pbkdf2("sha256", password, salt, iterations, 10)))
