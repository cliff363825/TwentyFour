# coding: utf-8

def addslashes(s):
    s = s.replace('\\', '\\\\') \
        .replace("'", "\\'") \
        .replace('"', '\\"') \
        .replace('\0', '\\0')
    return s


if __name__ == '__main__':
    s = "' \" \\ \0"
    print(s)
    print(addslashes(s))
