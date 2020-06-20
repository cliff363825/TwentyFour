# coding: utf-8

n = 10240099
b1 = (n & 0xff000000) >> 24
b2 = (n & 0x00ff0000) >> 16
b3 = (n & 0x0000ff00) >> 8
b4 = (n & 0x000000ff)
print(bytes([b1, b2, b3, b4]))

import struct

print(struct.pack('>I', n))

print(struct.unpack('>IH', b'\xf0\xf0\xf0\xf0\x80\x80'))
