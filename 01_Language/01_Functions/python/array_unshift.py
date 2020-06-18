# coding: utf-8

def array_unshift(arr, *args):
    arr[:0] = args
    return len(arr)


if __name__ == '__main__':
    queue = ["orange", "banana"]
    array_unshift(queue, "apple", "raspberry")
    print(queue)
