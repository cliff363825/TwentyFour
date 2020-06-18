# coding: utf-8

import magic

f = open("test.gif", mode="rb")
b = f.read(512)
f.close()
print(magic.from_buffer(b, mime=True))
