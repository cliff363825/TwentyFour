# coding: utf-8

from PIL import Image
import io


def getimagesizefromstring(imagedata):
    return Image.open(io.BytesIO(imagedata)).size


if __name__ == '__main__':
    with open('test.gif', mode='rb') as f:
        print(getimagesizefromstring(f.read()))
