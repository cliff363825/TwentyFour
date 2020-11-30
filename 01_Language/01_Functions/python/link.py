# coding: utf-8

import os

def link(target, link_name):
    os.link(target, link_name)
    return True

if __name__ == '__main__':
    link('test.txt', 'test1.txt')