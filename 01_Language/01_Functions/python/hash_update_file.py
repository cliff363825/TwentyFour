# coding: utf-8

def hash_update_file(context, filename):
    with open(filename, mode="rb") as f:
        while True:
            data = f.read(8192)
            if not data:
                break
            context.update(data)
