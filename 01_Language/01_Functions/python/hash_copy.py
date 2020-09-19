# coding: utf-8

import hashlib


def hash_copy(context):
    return context.copy()


if __name__ == '__main__':
    import get_class_methods

    print(get_class_methods.get_class_methods(hashlib))

    h = hashlib.new("md5")
    h.update(b"data")

    h1 = hash_copy(h)

    print(h.hexdigest())  # 8d777f385d3dfec8815d20f7496026dc

    h1.update(b"data")
    print(h1.hexdigest())  # 511ae0b1c13f95e5f08f1a0dd3da3d93

    h2 = hashlib.md5()
    h2.update(b"datadata")
    print(h2.hexdigest())  # 511ae0b1c13f95e5f08f1a0dd3da3d93
