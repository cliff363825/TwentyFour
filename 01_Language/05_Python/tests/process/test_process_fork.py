# coding: utf-8

import os

print('Process (%s) start...' % os.getpid())
# 只能在类Unix环境下执行
if os.name == 'nt':
    print('win不支持')
else:
    pid = os.fork()
    if pid == 0:
        # 子进程
        print('I am child process (%s) and my parent is %s' % (os.getpid(), os.getppid()))
    else:
        # 父进程
        print('I (%s) just created a child process (%s).' % (os.getpid(), pid))
