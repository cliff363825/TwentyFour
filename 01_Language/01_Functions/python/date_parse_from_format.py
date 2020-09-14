# coding: utf-8

import datetime

if __name__ == '__main__':
    d = datetime.datetime.strptime("15-Feb-2009", "%d-%b-%Y")
    print(d)
