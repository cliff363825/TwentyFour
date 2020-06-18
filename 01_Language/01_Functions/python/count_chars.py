# coding: utf-8

from collections import Counter


def count_chars(s, mode=0):
    b = s.encode()
    # python 3.6+: dict is ordered
    d = {x: 0 for x in range(0, 256)}
    d.update(Counter(b))
    if mode == 1:
        return {k: v for k, v in d.items() if v > 0}
    elif mode == 2:
        return {k: v for k, v in d.items() if v == 0}
    elif mode == 3:
        return "".join(chr(k) for k, v in d.items() if v > 0)
    elif mode == 4:
        return "".join(chr(k) for k, v in d.items() if v == 0)
    else:
        return d


if __name__ == '__main__':
    data = "Two Ts and one F."
    print(count_chars(data, 3))
    print(count_chars(data, 4))
    for k, v in count_chars(data, 1).items():
        print("[{}]: There were {} instance(s) of \"{}\" in the string.".format(k, v, chr(k)))
