# coding: utf-8

strings = {
    'string1': "\n\r\t",
    'string2': "\narf12",
    'string3': '\n\r\t',  # note the single quotes
    'string4': ' '
}
for k, v in strings.items():
    if v.isspace():
        print("The string " + k + " consists of whitespace characters only.")
    else:
        print("The string " + k + " contains non-whitespace characters.")
