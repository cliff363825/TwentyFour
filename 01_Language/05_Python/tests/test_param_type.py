# coding: utf-8

import inspect


def test_func(a, b, *args, c, d, **kwargs):
    pass


params = inspect.signature(test_func).parameters
print(params)
for name, param in params.items():
    print(param.kind)

# OrderedDict([('a', <Parameter "a">), ('b', <Parameter "b">), ('args', <Parameter "*args">), ('c', <Parameter "c">), ('d', <Parameter "d">), ('kwargs', <Parameter "**kwargs">)])
# POSITIONAL_OR_KEYWORD
# POSITIONAL_OR_KEYWORD
# VAR_POSITIONAL
# KEYWORD_ONLY
# KEYWORD_ONLY
# VAR_KEYWORD
