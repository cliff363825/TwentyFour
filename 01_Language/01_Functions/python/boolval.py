# coding: utf-8

def boolval(o):
    if o == "0":
        return False
    return bool(o)


if __name__ == '__main__':
    print('0:        ' + ('true' if boolval(0) else 'false'))
    print('42:       ' + ('true' if boolval(42) else 'false'))
    print('0.0:      ' + ('true' if boolval(0.0) else 'false'))
    print('4.2:      ' + ('true' if boolval(4.2) else 'false'))
    print('"":       ' + ('true' if boolval("") else 'false'))
    print('" ":      ' + ('true' if boolval(" ") else 'false'))
    print('"string": ' + ('true' if boolval("string") else 'false'))
    print('"true":   ' + ('true' if boolval("true") else 'false'))
    print('"false":  ' + ('true' if boolval("false") else 'false'))
    print('"0":      ' + ('true' if boolval("0") else 'false'))
    print('"1":      ' + ('true' if boolval("1") else 'false'))
    print('[1, 2]:   ' + ('true' if boolval([1, 2]) else 'false'))
    print('[]:       ' + ('true' if boolval([]) else 'false'))
    print('stdClass: ' + ('true' if boolval(object()) else 'false'))
