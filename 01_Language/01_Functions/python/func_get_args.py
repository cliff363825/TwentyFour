# coding: utf-8

def foo(*args, **kwargs):
    print("Number of arguments: ", len(args))
    if len(args) >= 2:
        print("Second argument is: ", args[1])

    for k, v in enumerate(args):
        print("Argument %d is: " % k, v)


foo(1, 2, 3)
