# coding: utf-8

# https://www.php2python.com/wiki/function.base-convert/
def base_convert(number, frombase, tobase):
    try:
        # Convert number to base 10
        base10 = int(number, frombase)
    except ValueError:
        raise

    if tobase < 2 or tobase > 36:
        raise NotImplementedError

    output_value = ''
    digits = "0123456789abcdefghijklmnopqrstuvwxyz"
    sign = ''

    if base10 == 0:
        return '0'
    elif base10 < 0:
        sign = '-'
        base10 = -base10

    # Convert to base toBase
    s = ''
    while base10 != 0:
        r = base10 % tobase
        r = int(r)
        s = digits[r] + s
        base10 //= tobase

    output_value = sign + s
    return output_value


if __name__ == '__main__':
    print(base_convert("a37334", 16, 2))
