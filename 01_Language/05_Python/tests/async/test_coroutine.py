# coding: utf-8

def consumer():
    r = ''
    while True:
        # 从produce拿到send(n)中的消息(n)，往下执行，再将结果(r)yield出去
        n = yield r
        if not n:
            return 'END'  # raise StopIteration('END')
        print('[CONSUMER] Consuming %s ...' % n)
        r = '200 OK'


def produce(c):
    # c.send(None)启动generator
    r = c.send(None)  # prime c
    print('[PRODUCER]', r)
    for n in [1, 2, 3, 4, False, 5, 6]:
        print('[PRODUCER] Producing %s ...' % n)
        # 切换到consumer执行
        try:
            r = c.send(n)
        except StopIteration as e:
            print('[PRODUCER] Consumer exception: %s' % e)
        else:
            print('[PRODUCER] Consumer return: %s' % r)
    # 关闭consumer generator
    c.close()


c = consumer()
produce(c)
