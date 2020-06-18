# coding: utf-8

def array_push(arr, *args):
    if isinstance(arr, dict):
        max_idx = -1
        for k in arr.keys():
            if isinstance(k, int) and k > max_idx:
                max_idx = k
        for i in args:
            max_idx += 1
            arr[max_idx] = i
    else:
        arr.extend(args)
    return len(arr)


if __name__ == '__main__':
    stack = ["orange", "banana"]
    print(array_push(stack, "apple", "raspberry"))
    print(stack)

    stack = {"k1": "orange", 2: "banana"}
    print(array_push(stack, "apple", "raspberry"))
    print(stack)
