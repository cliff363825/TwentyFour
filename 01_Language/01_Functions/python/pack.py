# coding: utf-8

import struct


def pack(f, *args):
    return struct.pack(f, *args)
