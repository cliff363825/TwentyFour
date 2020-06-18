# coding: utf-8

with open("test.txt", mode="r") as f:
    f.read(12)
    print(f.tell())
