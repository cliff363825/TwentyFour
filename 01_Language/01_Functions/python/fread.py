# coding: utf-8

import os

size = os.path.getsize("test.txt")

with open("test.txt", mode="r") as f:
    print(f.read(size))
