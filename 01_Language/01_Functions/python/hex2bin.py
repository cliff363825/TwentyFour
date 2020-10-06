# coding: utf-8

def hex2bin(data):
    return bytes.fromhex(data)

if __name__ == '__main__':
    h = hex2bin("6578616d706c65206865782064617461")
    print(h)