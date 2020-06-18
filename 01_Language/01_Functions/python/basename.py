# coding: utf-8

import os


def basename(path, suffix=None):
    res = os.path.basename(os.path.normpath(path))
    if suffix is not None:
        if res.endswith(suffix):
            res = res[:-len(suffix)]
    return res


if __name__ == '__main__':
    print(basename("/etc/sudoers.d", ".d"))
    print(basename("/etc/sudoers.d"))
    print(basename("/etc/passwd"))
    print(basename("/etc/passwd?a=b"))
    print(basename("/etc/"))
    print(basename("."))
    print(basename("/"))
