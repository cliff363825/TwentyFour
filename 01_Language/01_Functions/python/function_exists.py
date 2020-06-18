# coding: utf-8
import types


def function_exists(function_name):
    try:
        f = globals()[function_name]
        return isinstance(f, types.FunctionType)
    except KeyError:
        try:
            f = getattr(__builtins__, function_name)
            return isinstance(f, types.BuiltinFunctionType)
        except AttributeError:
            pass
    return False


if __name__ == '__main__':
    def foo():
        pass

    print(function_exists("foo"))
    print(function_exists("abs"))
    print(function_exists("abs1"))
