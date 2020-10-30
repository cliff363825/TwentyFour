# coding: utf-8

def is_scalar(var):
    return isinstance(var, (type(None), str, int, float, bool, bytes))
