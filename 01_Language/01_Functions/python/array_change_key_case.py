# coding: utf-8

def array_change_key_case(d, case="CASE_LOWER"):
    f = str.lower if (case is None or case == "CASE_LOWER") else str.upper
    return dict((f(k), v) for k, v in d.items())


if __name__ == '__main__':
    d = dict([("FirSt", 1), ("SecOnd", 4)])
    print(array_change_key_case(d, "CASE_UPPER"))
