# coding: utf-8

def lcfirst(s):
    if s is None:
        return ''
    s = str(s)
    if len(s) == 0:
        return ''
    return s[0].lower() + s[1:]

if __name__ == '__main__':
    print(lcfirst('HelloWorld'))
    print(lcfirst('HELLO WORLD'))
    print(lcfirst(' Abc'))
    print(lcfirst(None))