# coding: utf-8

def dechex(number):
    return hex(number & 0xffffffff)[2:]


if __name__ == '__main__':
    print(dechex(10))
    print(dechex(-10))
    print(dechex(47))
