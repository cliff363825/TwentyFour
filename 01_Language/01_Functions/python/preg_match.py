# coding: utf-8

import re


def preg_match(pattern, subject):
    p = re.compile(pattern)
    return p.search(subject) is not None


if __name__ == '__main__':
    print(preg_match('php', 'PHP is the web scripting language of choice.'))

    p = re.compile('php', re.I)
    print(p.search('PHP is the web scripting language of choice.'))
