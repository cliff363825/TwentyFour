# coding: utf-8

def natcasesort(array):
    array.sort(key=lambda x: '' if x is None else str(x).lower())


if __name__ == '__main__':
    array1 = array2 = ['IMG0.png', 'img12.png', 'img10.png', 'img2.png', None, 'img1.png', 'IMG3.png']
    natcasesort(array1)
    print(array1)
