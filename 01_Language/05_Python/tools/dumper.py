# coding: utf-8

class dumper(object):
    @staticmethod
    def dump(v):
        for x in dir(v):
            print(x, '=>', repr(getattr(v, x)))
