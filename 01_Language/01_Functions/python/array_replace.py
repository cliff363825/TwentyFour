# coding: utf-8

def array_replace(*args):
    d = {}
    for i in args:
        if isinstance(i, dict):
            for k, v in i.items():
                d[k] = v
        else:
            for k, v in enumerate(i):
                d[k] = v
    return d


if __name__ == '__main__':
    base = ["orange", "banana", "apple", "raspberry"]
    replacements = {0: "pineapple", 4: "cherry"}
    replacements2 = {0: "grape"}

    print(array_replace(base, replacements, replacements2))
