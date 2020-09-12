# coding: utf-8

import resource


def getrusage(who=0):
    return resource.getrusage(who)


if __name__ == '__main__':
    print(getrusage())
