# coding: utf-8

import fcntl

if __name__ == '__main__':
    f = open("test.txt", "r+")
    fcntl.flock(f, fcntl.LOCK_EX)
    f.truncate(0)
    f.write("hello world")
    f.flush()
    fcntl.flock(f, fcntl.LOCK_UN)
    f.close()
