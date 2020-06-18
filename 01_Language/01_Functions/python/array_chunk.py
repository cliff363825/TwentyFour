# coding: utf-8

def array_chunk(l, size, preserve_keys=False):
    res = []

    item = None
    if isinstance(l, dict):
        for k, v in l.items():
            if item is None or len(item) == size:
                item = {} if preserve_keys else []
                res.append(item)
            if preserve_keys:
                item[k] = v
            else:
                item.append(v)
    else:
        for v in l:
            if item is None or len(item) == size:
                item = []
                res.append(item)
            item.append(v)

    return res


if __name__ == '__main__':
    arr = ['a', 'b', 'c', 'd', 'e']
    print(array_chunk(arr, 2))
    d = {
        "k1": "v1",
        "k2": "v2",
        "k3": "v3",
        "k4": "v4",
        "k5": "v5",
    }
    print(array_chunk(d, 2, True))
    print(array_chunk(d, 2, False))
