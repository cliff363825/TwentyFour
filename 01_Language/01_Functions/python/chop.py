# coding: utf-8

text = "\t\tThese are a few words :) ...  "
# binary = "\x09Example string\x0A"
hello = "Hello World"
print(repr(text))
# print(repr(binary))
print(repr(hello))

print(repr(text.rstrip()))
print(repr(text.rstrip(" \t.")))
print(repr(hello.rstrip("Hdle")))

# trim the ASCII control characters at the end of $binary
# (from 0 to 31 inclusive)
# print(repr(binary.rstrip("\x00..\x1F")))
