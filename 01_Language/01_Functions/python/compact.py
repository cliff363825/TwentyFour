# coding: utf-8

import inspect


# https://www.php2python.com/wiki/function.compact/
def compact(*args):
    caller = inspect.stack()[1][0]  # caller of compact()
    res = {}
    for n in args:
        if n in caller.f_locals:
            res[n] = caller.f_locals[n]
        elif n in caller.f_globals:
            res[n] = caller.f_globals[n]
    return res


if __name__ == '__main__':
    city = "San Francisco"
    state = "CA"
    event = "SIGGRAPH"

    result = compact("event", "city", "state")
    print(result)
