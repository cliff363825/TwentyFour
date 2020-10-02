# coding: utf-8

import hmac


def hash_hmac_file(alog, filename, key):
    with open(filename, mode="rb") as f:
        h = hmac.new(key, None, alog)
        while True:
            data = f.read(8192)
            if not data:
                break
            h.update(data)
        return h.hexdigest()


if __name__ == '__main__':
    import file_put_contents

    file_put_contents.file_put_contents("test.txt", "The quick brown fox jumped over the lazy dog.")
    print(hash_hmac_file("md5", "test.txt", b"secret"))
