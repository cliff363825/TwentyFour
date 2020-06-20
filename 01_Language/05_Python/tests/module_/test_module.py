# coding: utf-8

import a.a

print(a)
print(a.a)
print(a.a.a)
'''
<module 'a' (namespace)>
<module 'a.a' from 'D:\\phpStudy\\PHPTutorial\\WWW\\git\\my_python\\tests\\a\\a.py'>
<class 'a.a.a'>
'''

import b.b

print(b)
print(b.b)
print(b.b.b)
'''
<module 'b' from 'D:\\phpStudy\\PHPTutorial\\WWW\\git\\my_python\\tests\\b\\__init__.py'>
<module 'b.b' from 'D:\\phpStudy\\PHPTutorial\\WWW\\git\\my_python\\tests\\b\\b.py'>
<class 'b.b.b'>
'''

import c

print(c)
print(c.c)
print(c.c.c)
'''
<module 'c' from 'D:\\phpStudy\\PHPTutorial\\WWW\\git\\my_python\\tests\\c\\__init__.py'>
<module 'c.c' from 'D:\\phpStudy\\PHPTutorial\\WWW\\git\\my_python\\tests\\c\\c.py'>
<class 'c.c.c'>
'''
