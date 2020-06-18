# coding: utf-8

def escapeshellarg(arg):
    return "\\'".join("'" + p + "'" for p in arg.split("'"))


if __name__ == '__main__':
    print("ls " + escapeshellarg("./"))
    print("ls " + escapeshellarg("','"))
