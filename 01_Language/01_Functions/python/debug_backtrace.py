# coding: utf-8

import traceback

s1 = traceback.extract_stack()
s2 = traceback.format_stack()
print(s1)
print(s2)
traceback.print_stack()
