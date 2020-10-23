# coding: utf-8

def is_array(v):
    return isinstance(v, (tuple, list))

if __name__ == '__main__':
    yes = ['this', 'is', 'an array']
    print(is_array(yes))

    no = 'this is a string'
    print(is_array(no))