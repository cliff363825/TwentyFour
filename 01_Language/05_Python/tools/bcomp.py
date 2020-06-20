# coding: utf-8

import sys, os

if __name__ == '__main__':
    if sys.platform == 'darwin':
        bcomp_data_file = '~/Library/Application Support/Beyond Compare/registry.dat'
        bcomp_data_file = os.path.expanduser(bcomp_data_file)
        if os.path.exists(bcomp_data_file) and os.path.isfile(bcomp_data_file):
            yn = input('Are you sure to delete this file ' + bcomp_data_file + ' [y/n]: ') # type: str
            if yn and yn.lower() == 'y':
                os.remove(bcomp_data_file)
                print('Success!')
            else:
                print('Fail!')
        else:
            print('File not found!')
    else:
        print('Only support mac!')
