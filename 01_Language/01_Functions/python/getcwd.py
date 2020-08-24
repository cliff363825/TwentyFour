# coding: utf-8

import os


def getcwd():
    return os.getcwd()


if __name__ == '__main__':
    print(getcwd())
    os.chdir("../")
    print(getcwd())
