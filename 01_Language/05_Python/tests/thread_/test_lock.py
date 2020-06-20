# coding: utf-8

import time, threading

# 假定这是你的银行存款
balance = 0
lock = threading.Lock()


def change_it(n):
    # 先存后去，结果应该为0
    global balance
    balance += n
    balance -= n


def run_thread(n):
    global lock
    for i in range(1000000):
        # 第一种方法： try:...finally:lock.release()
        # lock.acquire()
        # try:
        #     change_it(n)
        # finally:
        #     lock.release()
        # 第二种方法： with lock:
        with lock:
            change_it(n)


t1 = threading.Thread(target=run_thread, args=(5,))
t2 = threading.Thread(target=run_thread, args=(8,))
t1.start()
t2.start()
t1.join()
t2.join()
print(balance)
