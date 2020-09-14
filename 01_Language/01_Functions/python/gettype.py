# coding: utf-8

def gettype(o):
    return type(o).__name__


if __name__ == '__main__':
    arr = [1, 1., None, object(), "foo"]
    for v in arr:
        print(gettype(v))
