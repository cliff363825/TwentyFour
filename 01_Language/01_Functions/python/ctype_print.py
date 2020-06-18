# coding: utf-8

strings = {'string1': "asdf\n\r\t", 'string2': 'arf12', 'string3': 'LKA#@%.54', "string4": "\t"}
for k, v in strings.items():
    if v.isprintable():
        print("The string " + k + " consists of all printable characters.")
    else:
        print("The string " + k + " does not consist of all printable characters.")
