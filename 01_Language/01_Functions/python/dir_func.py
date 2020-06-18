# coding: utf-8

import os

files = os.listdir("./")
print(files)

dirpath, dirnames, filenames = next(os.walk("./"))
for filename in filenames:
    print(os.path.abspath(os.path.join(dirpath, filename)))
