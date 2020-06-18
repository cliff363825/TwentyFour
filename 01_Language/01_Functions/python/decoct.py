# coding: utf-8

def dechex(number):
    return oct(number & 0xffffffff)[2:]


if __name__ == '__main__':
    print(dechex(15))
    print(dechex(-15))
    print(dechex(264))
