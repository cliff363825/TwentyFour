# coding: utf-8

import fnmatch

colors = ["agray", "egrey", "aegraey"]
for c in colors:
    if fnmatch.fnmatch(c, "*gr[ae]y"):
        print(c, "=>", "some form of gray ...")
