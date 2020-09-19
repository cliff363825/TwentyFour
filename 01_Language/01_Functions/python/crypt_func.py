# coding: utf-8

import crypt
import hmac

if __name__ == '__main__':
    hashed_password = crypt.crypt("mypassword")
    print(hashed_password)
    user_input = "mypassword1mmmmmmm"
    if hmac.compare_digest(hashed_password, crypt.crypt(user_input, hashed_password)):
        print("Password verified!")

    print("Standard DES: ", crypt.crypt("rasmuslerdorf", "rl"))
    print("Extended DES: ", crypt.crypt("rasmuslerdorf", "_J9..rasm"))
    print("MD5:          ", crypt.crypt("rasmuslerdorf", '$1$rasmusle$'))
    print("Blowfish:     ", crypt.crypt("rasmuslerdorf", "$2a$07$usesomesillystringforsalt$"))
    print("SHA-256:      ", crypt.crypt("rasmuslerdorf", "$5$rounds=5000$usesomesillystringforsalt$"))
    print("SHA-512:      ", crypt.crypt("rasmuslerdorf", "$6$rounds=5000$usesomesillystringforsalt$"))