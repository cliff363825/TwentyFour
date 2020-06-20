# coding: utf-8

def writer():
    """A coroutine that writers data *sent* to it to fd, socket, etc"""
    while True:
        try:
            w = (yield)
        except SpamException as e:
            print('***')
        else:
            print('>> ', w)


def writer_wrapper(coro):
    coro.send(None)  # prime core
    while True:
        try:
            try:
                x = (yield)
            except Exception as e:
                coro.throw(e)
            else:
                coro.send(x)
        except StopIteration:
            pass


def writer_wrapper2(coro):
    yield from coro


class SpamException(Exception):
    pass


w = writer()
wrap = writer_wrapper2(w)
wrap.send(None)  # prime writer_wrapper
# for i in range(4):
#     wrap.send(i)
# wrap.close()
for i in [0, 1, 2, 'spam', 4]:
    if i == 'spam':
        wrap.throw(SpamException)
    else:
        wrap.send(i)
