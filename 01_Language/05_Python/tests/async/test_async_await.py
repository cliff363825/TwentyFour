# coding: utf-8

import asyncio


@asyncio.coroutine  # 该装饰器用于标记为coroutine
def hello():
    print('hello world!')
    r = yield from asyncio.sleep(1)  # yield from: 该函数是 delegate generator(委托生成器)
    print('hello again!')


async def hello2():
    print('hello world!')
    r = await asyncio.sleep(1)
    print('hello again!')


loop = asyncio.get_event_loop()
# fs = [hello(), hello(), hello()]
fs = [hello2(), hello2(), hello2()]
loop.run_until_complete(asyncio.wait(fs))
loop.close()
