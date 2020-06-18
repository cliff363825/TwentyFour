# coding: utf-8

def array_walk(d, cb, *args):
    for k, v in d.items():
        cb(v, k, d, *args)


if __name__ == '__main__':
    fruits = {"d": "lemon", "a": "orange", "b": "banana", "c": "apple"}


    def test_alter(item1, key, arr, prefix):
        arr[key] = prefix + ":" + item1


    def test_print(item2, key, arr):
        print(key + ":" + item2)


    print("Before ...:")
    array_walk(fruits, test_print)
    array_walk(fruits, test_alter, 'fruit')
    print("... and after:")
    array_walk(fruits, test_print)
