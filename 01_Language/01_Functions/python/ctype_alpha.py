# coding: utf-8

strings = ['KjgWZC', 'arf12', '']
for s in strings:
    if s.isalpha():
        print("The string " + s + " consists of all letters.")
    else:
        print("The string " + s + " does not consist of all letters.")
