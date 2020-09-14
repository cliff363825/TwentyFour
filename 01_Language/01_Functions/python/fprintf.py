# coding: utf-8

import time


def fprintf(fd, fmt, *args):
    print(fmt.format(args), file=fd)


if __name__ == '__main__':
    f = open("test.txt", mode="w")
    ltime = time.localtime()
    print("%04d-%02d-%02d" % (ltime.tm_year, ltime.tm_mon, ltime.tm_mday), file=f)
    f.close()
