# coding: utf-8

import os


def chdir(path):
    os.chdir(path)


if __name__ == '__main__':
    print(os.getcwd())
    os.chdir("../")
    print(os.getcwd())
