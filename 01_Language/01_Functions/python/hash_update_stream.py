# coding: utf-8

def hash_update_stream(context, handle):
    while True:
        data = handle.read(8192)
        if not data:
            break
        context.update(data)
