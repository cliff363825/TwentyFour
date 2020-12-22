# coding: utf-8

from os import path


def pathinfo(p):
    filename, ext = path.splitext(path.basename(p))
    return {
        'dirname': path.dirname(p),
        'basename': path.basename(p),
        'extension': ext,
        'filename': filename,
    }


if __name__ == '__main__':
    print(pathinfo('./text.txt'))
    print(pathinfo('./text'))
    print(pathinfo('./.a'))
    # print(path.splitext('./.a'))
