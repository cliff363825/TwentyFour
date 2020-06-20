# coding: utf-8

import logging

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)-8s %(filename)s:%(lineno)-4d: %(message)s',
                    datefmt='%Y-%m-%d %H:%M:%S')

s = '0'
n = int(s)
logging.info('n = %d' % n)
print(10 / n)
