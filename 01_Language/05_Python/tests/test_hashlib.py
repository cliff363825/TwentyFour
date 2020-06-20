# coding: utf-8

import hashlib

md5 = hashlib.md5()
md5.update('123456'.encode('utf-8'))
print(md5.digest())
print(md5.hexdigest())
a = md5.digest()
hex_a = ''
for i in range(0, len(a)):
    hex_a += '%02x' % a[i]

print(hex_a)

md5_2 = hashlib.md5()
md5_2.update('123'.encode('utf-8'))
md5_2.update('456'.encode('utf-8'))
print(md5_2.hexdigest())
