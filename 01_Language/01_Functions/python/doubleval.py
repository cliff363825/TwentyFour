# coding: utf-8

def doubleval(s):
    if s is None or len(s) == 0:
        return 0.0
    res = ""
    digit = False
    sign = False
    dot = False
    for c in s:
        if '0' <= c <= '9':
            res += c
            digit = True
        elif c == '+' or c == '-':
            if digit or sign or dot:
                break
            res += c
            sign = True
        elif c == '.':
            if dot:
                break
            if not digit:
                res += '0'
                digit = True
            res += c
            dot = True
        elif c == ' ':
            if digit or sign or dot:
                break
        else:
            if digit or sign or dot:
                break
            return 0.0
    if not digit:
        return 0.0
    if res.endswith("."):
        res = res[:-1]
    return float(res)


if __name__ == '__main__':
    print(doubleval("122.34343The"))
    print(doubleval("+122.34343."))
    print(doubleval("-122.34343.1"))
    print(doubleval("-.34343.1"))
    print(doubleval("The122.34343"))
