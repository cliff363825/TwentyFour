# coding: utf-8

import re


def nl2br(string, is_xhtml=True):
    pattern = re.compile('(\r\n|\n\r|\n|\r)')
    return pattern.sub(r'<br />\1' if is_xhtml else r'<br>\1', string)


if __name__ == '__main__':
    print(nl2br("foo isn't\n bar"))
    print(nl2br("foo isn't\r bar"))
    print(nl2br("foo isn't\r\n bar"))
    print(nl2br("foo isn't\r\n bar", False))
