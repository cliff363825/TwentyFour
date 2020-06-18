# coding: utf-8

def array_keys(d, *args):
    if len(args) > 0:
        return array_keys_extra(d, *args)

    if isinstance(d, dict):
        return list(d.keys())
    else:
        return [k for k, _ in enumerate(d)]


def array_keys_extra(d, value):
    if isinstance(d, dict):
        return [k for k, v in d.items() if v == value]
    else:
        return [k for k, v in enumerate(d) if v == value]


if __name__ == '__main__':
    array = {0: 100, "color": "red"}
    print(array_keys(array))

    array1 = ["blue", "red", "green", "blue", "blue"]
    print(array_keys(array1, "blue"))

    array2 = {
        "color": ["blue", "red", "green"],
        "size": ["small", "medium", "large"]
    }
    print(array_keys(array2))
