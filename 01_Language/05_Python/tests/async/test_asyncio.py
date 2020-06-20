# coding: utf-8

import asyncio
import threading


@asyncio.coroutine  # 该装饰器用于标记为coroutine
def hello():
    print('Hello world! (%s)' % threading.current_thread())
    r = yield from asyncio.sleep(1)  # yield from: 该函数是 delegate generator(委托生成器)
    print(r, type(r))
    print('Hello again! (%s)' % threading.current_thread())


loop = asyncio.get_event_loop()
tasks = [hello(), hello()]
loop.run_until_complete(asyncio.wait(tasks))
loop.close()
