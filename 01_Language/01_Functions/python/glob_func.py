# coding: utf-8

import glob as _glob
import filesize

def glob(pattern):
    return _glob.glob(pattern)

if __name__ == '__main__':
    for f in glob("*.py"):
        print("{0} size {1}".format(f, filesize.filesize(f)))