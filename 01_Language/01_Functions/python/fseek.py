# coding: utf-8

with open("test.txt", mode="r") as f:
    print(f.readline())
    print(f.readline())
    f.seek(0)
    print(f.readline())
