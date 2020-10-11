# coding: utf-8

def in_array(needle, haystack):
    return needle in haystack

if __name__ == '__main__':
    os = ["Mac", "NT", "Irix", "Linux"]
    print(in_array("Irix", os))
    print(in_array("mac", os))