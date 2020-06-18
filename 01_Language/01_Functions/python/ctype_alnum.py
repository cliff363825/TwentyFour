# coding: utf-8

strings = ['AbCd1zyZ9', 'foo!#$bar', '']
for s in strings:
    if s.isalnum():
        print("The string " + s + " consists of all letters or digits.")
    else:
        print("The string " + s + " does not consist of all letters or digits.")
