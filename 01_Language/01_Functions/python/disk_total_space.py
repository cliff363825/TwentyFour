# coding: utf-8

import os
import sys
import shutil


def disk_total_space(directory):
    if sys.version_info >= (3, 3):
        df = shutil.disk_usage(directory)
        return df.total
    else:
        tmp = os.statvfs(directory)
        return tmp.f_blocks * tmp.f_frsize


if __name__ == '__main__':
    print(disk_total_space("/"))
