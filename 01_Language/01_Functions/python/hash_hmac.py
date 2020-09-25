# coding: utf-8

import hmac


def hash_hmac(algo, data, key, raw_output=False):
    h = hmac.new(key, data, algo)
    if raw_output:
        return h.digest()
    else:
        return h.hexdigest()


if __name__ == '__main__':
    print(hash_hmac("ripemd160", b"The quick brown fox jumped over the lazy dog.", b"secret"))
