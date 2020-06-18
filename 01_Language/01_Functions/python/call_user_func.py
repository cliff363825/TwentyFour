# coding: utf-8

def call_user_func(cb, *args):
    return cb(*args)


if __name__ == '__main__':
    def barber(t):
        print('You wanted a {} haircut, no problem'.format(t))


    call_user_func(barber, "mushroom")
    call_user_func(barber, "shave")
    print(call_user_func(lambda x, y: x + y, 100, 200))
