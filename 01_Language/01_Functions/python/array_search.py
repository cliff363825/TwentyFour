# coding: utf-8

def array_search(needle, arr):
    if isinstance(arr, dict):
        for k, v in arr.items():
            if v == needle:
                return k
    else:
        try:
            return arr.index(needle)
        except ValueError:
            return False
    return False


if __name__ == '__main__':
    l = ["blue", "red", "green", "red"]
    print(array_search("green", l))
    print(array_search("red", l))
