# coding: utf-8

def iconv(in_charset, out_charset, s):
    return s.encode(in_charset).decode(out_charset)


if __name__ == '__main__':
    text = "This is the Euro symbol '€'."
    print(text)
    print(iconv("utf-8", "iso-8859-1", text))
