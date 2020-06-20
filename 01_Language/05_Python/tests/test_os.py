# coding: utf-8

import os

print(os.name)
# print(getattr(os, 'uname'))
print(hasattr(os, 'uname'))
print(os.environ)
print(os.environ.get('path'))

print(os.path.realpath('.'))
print(os.path.join('/a/b', 'c'))
# print(os.mkdir(os.path.realpath(os.path.join(__file__,os.pardir, 'test_path'))))
# print(os.rmdir(os.path.realpath(os.path.join(__file__,os.pardir, 'test_path'))))
print(os.path.split('/a/b/c/d.txt'))
print(os.path.splitext('/a/b/c/d.txt'))

import shutil

# shutil.copyfile('test_chain.py', 'test_chain.txt')
r = [x for x in os.listdir('.') if os.path.isdir(x)]
print(r)
r = [x for x in os.listdir('.') if os.path.isfile(x) and os.path.splitext(x)[1] == '.py']
print(r)
