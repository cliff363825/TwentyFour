# coding: utf-8

import re

pattern = re.compile(r'百度为您找到相关结果约(\d+)个', re.I | re.S | re.M | re.U)
match = pattern.search("\n百度为您找到相关结果约10000个\n")
print(match.group(1))

pattern = r'[^\u4e00-\u9fa5_a-zA-Z0-9]'
print(re.sub(pattern, "", "！！！《呵呵》？？呵呵？"))

r = re.match(r'^(\d{3})-(\d{3,8})$', '010-12345')
print(r)
print(r.group(0), r.group(1), r.group(2), r.groups())

r = re.match(r'^\d{3}-\d{3,8}$', '010 12345')
print(r)

print(re.split(r'\s+', 'a b  c'))
print(re.split(r'[\s,]+', 'a,b, c  d'))
print(re.split(r'[\s,;]+', 'a,b;; c  d'))

print(re.match(r'^(\d+)(0*)$', '102300').groups())
print(re.match(r'^(\d+?)(0*)$', '102300').groups())

re_telephone = re.compile(r'^(\d{3})-(\d{3,8})$')
print(re_telephone.match('010-12345').groups())
print(re_telephone.match('010-8086').groups())

# re.match() vs re.search()
string_with_newlines = 'something\nsomeotherthing'

print(re.match('some', string_with_newlines))  # matches
print(re.match('someother', string_with_newlines))  # won't match
print(re.match('^someother', string_with_newlines, re.MULTILINE))  # also won't match
print(re.search('someother', string_with_newlines))  # finds something
print(re.search('^someother', string_with_newlines, re.MULTILINE))  # also finds something

m = re.compile('thing$', re.MULTILINE)

print(m.match(string_with_newlines))  # no match
print(m.match(string_with_newlines, pos=4))  # matches
print(m.search(string_with_newlines, re.MULTILINE))  # also matches
