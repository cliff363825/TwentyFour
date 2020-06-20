# coding: utf-8

import sys

print(id(sys))
os = __import__('os', globals(), locals(), ['path'])
print(os)
print(os.path)
