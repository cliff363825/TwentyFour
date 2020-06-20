# coding: utf-8
import threading
from time import sleep


def my_thread(word):
    sleep(1)
    print(threading.current_thread().getName(), word)


for i in range(5):
    threading.Thread(target=my_thread, args=("say hello",)).start()

print("main thread over")
