# coding: utf-8

def ltrim(s, charlist=None):
    if charlist is None:
        return s.lstrip()
    return s.lstrip(charlist)


if __name__ == '__main__':
    text = "\t\tThese are a few words :) ...  "
    binary = "\x09Example string\x0A"
    hello = "Hello World"
    print(text)
    print(binary)
    print(hello)

    trimmed = ltrim(text)
    print(trimmed)

    trimmed = ltrim(text, " \t.")
    print(trimmed)

    trimmed = ltrim(hello, "Hdle")
    print(trimmed)
