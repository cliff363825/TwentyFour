# coding: utf-8

import psutil


def memory_get_usage():
    process = psutil.Process()
    return process.memory_info().rss


if __name__ == '__main__':
    print(memory_get_usage() / 1024 / 1024, 'MB')
