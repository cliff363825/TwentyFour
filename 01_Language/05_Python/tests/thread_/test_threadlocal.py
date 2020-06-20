# coding: utf-8

import threading, logging

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)-8s %(filename)s:%(lineno)-4d: %(message)s',
                    datefmt='%Y-%m-%d %H:%M:%S')

local_school = threading.local()


def process_student():
    # 获取当前线程关联的student
    std = local_school.student
    print('Hello, %s (in %s)' % (std, threading.current_thread().name))


def process_thread(name):
    # 绑定ThreadLocal的student:
    local_school.student = name
    process_student()


# t1 = threading.Thread(target=process_thread, args=('Alice',), name='Thread-A')
# t2 = threading.Thread(target=process_thread, args=('Bob',), name='Thread-B')
# t1.start()
# t2.start()
# t1.join()
# t2.join()

student = dict()


def process_thread2(name):
    student[threading.current_thread()] = name
    process_student2()


def process_student2():
    name = student[threading.current_thread()]
    print('Hello, %s (in %s)' % (name, threading.current_thread().name))


t1 = threading.Thread(target=process_thread2, args=('Alice',), name='Thread-A')
t2 = threading.Thread(target=process_thread2, args=('Bob',), name='Thread-B')
t1.start()
t2.start()
t1.join()
t2.join()
