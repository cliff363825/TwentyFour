# coding: utf-8

from PIL import Image


def getimagesize(filename):
    return Image.open(filename).size


if __name__ == '__main__':
    print(getimagesize('test.gif'))
