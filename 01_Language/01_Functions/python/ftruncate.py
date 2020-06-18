# coding: utf-8

with open("test.txt", mode="r+") as f:
    f.truncate(10)
    f.seek(0)
    print(f.read())
