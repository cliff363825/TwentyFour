# coding: utf-8

import magic

with open("test.gif", mode="rb") as f:
    buf = f.read(512)
    print(magic.from_buffer(buf, mime=True))
