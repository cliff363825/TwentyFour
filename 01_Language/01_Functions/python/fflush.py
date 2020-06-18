# coding: utf-8

f = open("test.txt", mode="r+")
f.write("hello world")
f.flush()
f.truncate(f.tell())
f.close()
