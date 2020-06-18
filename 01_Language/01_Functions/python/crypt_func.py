# coding: utf-8

import crypt
from hmac import compare_digest as hash_equals

if __name__ == '__main__':

    hashed_password = crypt.crypt("mypassword")
    print(hashed_password)
    user_input = "mypassword1mmmmmmm"
    if hash_equals(hashed_password, crypt.crypt(user_input, hashed_password)):
        print("Password verified!")
