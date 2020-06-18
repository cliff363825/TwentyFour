# coding: utf-8

def ctype_cntrl(s):
    if s is None or len(s) == 0:
        return False
    for i in s:
        code = ord(i)
        if 0 <= code <= 31 or code == 127:
            continue
        return False
    return True


if __name__ == '__main__':
    strings = {'string1': "\n\r\t", 'string2': 'arf12', '': ''}
    for k, v in strings.items():
        if ctype_cntrl(v):
            print("The string '" + k + "' consists of all control characters.")
        else:
            print("The string '" + k + "' does not consist of all control characters.")
