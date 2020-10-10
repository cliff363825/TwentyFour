# coding: utf-8

def implode(glue, pieces):
    return glue.join(pieces)


if __name__ == '__main__':
    arr = ["lastname", "email", "phone"]
    print(implode(",", arr))
