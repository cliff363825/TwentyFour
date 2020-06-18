# coding: utf-8

def ctype_xdigit(s):
    if s is None or len(s) == 0:
        return False
    for i in s:
        code = ord(i)
        if 48 <= code <= 57 or \
                65 <= code <= 70 or \
                97 <= code <= 102:
            continue
        return False
    return True
