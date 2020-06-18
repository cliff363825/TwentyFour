# coding: utf-8

strings = ['1820.20', '10002', 'wsl!12']
for s in strings:
    if s.isdigit():
        print("The string " + s + " consists of all digits.")
    else:
        print("The string " + s + " does not consist of all digits.")
