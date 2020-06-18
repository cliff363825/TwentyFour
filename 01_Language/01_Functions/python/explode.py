# coding: utf-8

def explode(delimiter, string, limit=None):
    if limit is None:
        return string.split(delimiter)
    elif limit < 0:
        return string.split(delimiter)[:limit]
    elif limit == 0:
        return [string]
    else:
        return string.split(delimiter, limit - 1)


if __name__ == '__main__':
    pizza = "piece1 piece2 piece3 piece4 piece5 piece6"
    print(explode(" ", pizza))

    data = "foo:*:1023:1000::/home/foo:/bin/sh"
    print(explode(":", data))

    s = "one|two|three|four"
    print(explode("|", s, 2))
    print(explode("|", s, -1))
    print(explode("|", s, 0))
