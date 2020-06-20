# coding: utf-8

from multiprocessing import Process, Queue
import os, time, random


def write(q):
    """
    写数据进程执行的代码
    :type q: Queue
    """
    print('Process to write: %s' % os.getpid())
    for value in ['A', 'B', 'C']:
        print('Put %s to queue...' % value)
        q.put(value)
        time.sleep(random.random())


def read(q):
    """

    :type q: Queue
    """
    print('Process to read: %s' % os.getpid())
    while True:
        value = q.get(True)
        print('Get %s from queue.' % value)


print('Pid: %s, __name__: %s' % (os.getpid(), __name__))
if __name__ == '__main__':
    # 父进程创建Queue, 并传给各个子进程
    q = Queue()
    pw = Process(target=write, args=(q,))
    pr = Process(target=read, args=(q,))
    # 启动子进程pw, 写入
    pw.start()
    # 启动子进程pr, 读取
    pr.start()
    # 等待pw结束
    pw.join()
    # pr进程里是死循环，无法等待起结束，只能强行终止
    pr.terminate()
