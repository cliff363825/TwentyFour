# coding: utf-8

import os
from multiprocessing import Process


# 子进程要执行的代码
def run_proc(name):
    print('Run child process %s (%s)...' % (name, os.getpid()))


print('Pid: %s, __name__: %s' % (os.getpid(), __name__))
if __name__ == '__main__':
    print('Parent process %s.' % (os.getpid(),))
    p = Process(target=run_proc, args=('test',))
    print('Child process will start.')
    p.start()
    p.join()
    print('child process end.')

'''
on windows:

Pid: 22236, __name__: __main__
Parent process 22236.
Child process will start.
Pid: 17600, __name__: __mp_main__  # windows下会多这一行
Run child process test (17600)...
child process end.

on linux:

Pid: 11860, __name__: __main__
Parent process 11860.
Child process will start.
Run child process test (11861)...
child process end.
'''
