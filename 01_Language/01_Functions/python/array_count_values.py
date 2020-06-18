# coding: utf-8

def array_count_values(l):
    m = dict()
    for i in l:
        if m.get(i) is None:
            m[i] = 0
        m[i] += 1
    return m


# https://www.php2python.com/wiki/function.array-count-values/
def array_count_values2(l):
    from collections import defaultdict
    m = defaultdict(int)
    for i in l:
        m[i] += 1
    return m


# https://www.php2python.com/wiki/function.array-count-values/
def array_count_values3(l):
    from collections import Counter
    return Counter(l)


if __name__ == '__main__':
    array = [1, "hello", 1, "world", "hello"]
    print(array_count_values(array))
    print(array_count_values2(array))
    print(array_count_values3(array))
