# coding: utf-8

strings = ['aac123', 'qiutoas', 'QASsdks']
for s in strings:
    if s.islower():
        print("The string " + s + " consists of all lowercase letters.")
    else:
        print("The string " + s + " does not consist of all lowercase letters.")
