# coding: utf-8

def ctype_punct(s):
    if s is None or len(s) == 0:
        return False
    for i in s:
        code = ord(i)
        if 32 < code <= 47 or 58 <= code <= 64 or \
                91 <= code <= 96 or 123 <= code <= 126:
            continue
        return False
    return True
