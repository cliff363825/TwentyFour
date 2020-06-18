# coding: utf-8

def array_unique(arr):
    if isinstance(arr, dict):
        return list(set(arr.values()))
    return list(set(arr))


if __name__ == '__main__':
    input = {"a": "green", 0: "red", "b": "green", 1: "blue", 2: "red"}
    print(array_unique(input))
