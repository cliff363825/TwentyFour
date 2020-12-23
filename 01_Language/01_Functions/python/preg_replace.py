# coding: utf-8

import re


def preg_replace(pattern, replacement, subject):
    return re.sub(pattern, replacement, subject)


if __name__ == '__main__':
    print(preg_replace(r'(\w+) (\d+), (\d+)', r'\g<1>1,\g<3>', 'April 15, 2003'))
