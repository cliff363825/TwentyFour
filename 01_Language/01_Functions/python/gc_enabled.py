# coding: utf-8

import gc

print(gc.isenabled())
gc.disable()
print(gc.isenabled())
