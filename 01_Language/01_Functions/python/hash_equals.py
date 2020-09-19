# coding: utf-8

import hmac


def hash_equals(known_string, user_string):
    return hmac.compare_digest(known_string, user_string)


if __name__ == '__main__':
    import crypt

    expected = crypt.crypt("12345", "$2a$07$usesomesillystringforsalt$")
    correct = crypt.crypt("12345", "$2a$07$usesomesillystringforsalt$")
    incorrect = crypt.crypt("apple", "$2a$07$usesomesillystringforsalt$")

    print(hash_equals(expected, correct))
    print(hash_equals(expected, incorrect))
