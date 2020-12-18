# coding: utf-8

import configparser

if __name__ == '__main__':
    cfg = configparser.ConfigParser(strict=False)
    cfg.read('test.ini')
    for k, v in cfg.items():
        for k1, v1 in v.items():
            print(k, k1, v1)
