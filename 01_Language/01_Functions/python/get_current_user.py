# coding: utf-8

import os
import pwd
import inspect


def get_current_user():
    f = inspect.stack()[1][1]
    return pwd.getpwuid(os.stat(f).st_uid).pw_name

if __name__ == '__main__':
    print(get_current_user())
