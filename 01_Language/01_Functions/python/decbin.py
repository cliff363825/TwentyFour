# coding: utf-8

def decbin(number):
    return bin(number & 0xffffffff)[2:]


if __name__ == '__main__':
    print(decbin(12))
    print(decbin(-12))
    print(decbin(26))
