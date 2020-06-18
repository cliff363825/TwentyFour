# coding: utf-8

def ctype_graph(s):
    if s is None or len(s) == 0:
        return False
    for i in s:
        code = ord(i)
        if 32 < code <= 126:
            continue
        return False
    return True
