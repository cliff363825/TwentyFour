# coding: utf-8

import re


def preg_replace_callback(pattern, callback, subject):
    return re.sub(pattern, callback, subject)


if __name__ == '__main__':
    text = 'April fools day is 04/01/2002\n' + \
           'Last christmas was 12/24/2001\n'

    print(preg_replace_callback(r'(\d{2}/\d{2}/)(\d{4})', lambda m: m.group(1) + str(int(m.group(2)) + 1), text))
