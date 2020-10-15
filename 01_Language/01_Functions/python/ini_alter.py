# coding: utf-8

import os

def ini_alter(varname, newvalue):
    oldvalue = os.environ.get(varname)
    os.environ[varname] = newvalue
    return oldvalue

if __name__ == '__main__':
    print(ini_alter("display_errors", "1"))