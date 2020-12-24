# coding: utf-8

import re


def preg_split(pattern, subject):
    return re.split(pattern, subject)


if __name__ == '__main__':
    keywords = preg_split(r'[\s,]+', 'hypertext language, programming')
    print(keywords)
