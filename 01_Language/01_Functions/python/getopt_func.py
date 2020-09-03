# coding: utf-8

from getopt import getopt
import sys

if __name__ == '__main__':
    # INPUT:   python getopt_func.py -a 1 -b --required=2 --option
    # OUTPUT:  [('-a', '1'), ('-b', ''), ('--required', '2'), ('--option', '')] []
    opts, args = getopt(sys.argv[1:], '-a:-b-c', ['required=', 'option', 'opt'])
    print(opts, args)