# coding: utf-8

with open("test.txt", mode="rb") as f:
    while True:
        b = f.read(1)
        if b == b'':
            break
        print(b)
