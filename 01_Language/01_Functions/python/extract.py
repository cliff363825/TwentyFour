# coding: utf-8

import inspect


# https://www.php2python.com/wiki/function.extract/
def extract(vars):
    caller = inspect.stack()[1][0]  # caller of extract()
    for n, v in vars.items():
        caller.f_locals[n] = v  # NEVER DO THIS - not guaranteed to work
