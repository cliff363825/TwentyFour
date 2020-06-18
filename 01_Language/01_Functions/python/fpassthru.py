# coding: utf-8

f = open("test.txt", mode="r+")
print(f.read(1))
f.seek(0)
print(f.read())
f.close()
