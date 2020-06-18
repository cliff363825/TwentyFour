# coding: utf-8

from doubleval import doubleval


def floatval(s):
    return doubleval(s)


if __name__ == '__main__':
    print(floatval("122.34343The"))
    print(floatval("+122.34343."))
    print(floatval("-122.34343.1"))
    print(floatval("-.34343.1"))
    print(floatval("The122.34343"))
