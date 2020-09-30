# coding: utf-8

def file_put_contents(filename, data, append=False):
    mode = "a" if append else "w"
    with open(filename, mode=mode) as f:
        f.write(data)


if __name__ == '__main__':
    from file_get_contents import file_get_contents

    file_put_contents("test.txt", "John Smith\n", True)
    print(file_get_contents("test.txt"))
