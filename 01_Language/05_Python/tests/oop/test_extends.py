# coding: utf-8

class A(object):
    def test(self, end=False):
        print('call A test...')
        if not end:
            self.test(True)

    def test2(self):
        print('call A test2...')
        pass


class B(A):
    def test(self, end=False):
        print('call B test...')
        super(B, self).test(end)

    def test2(self):
        print('call B test2...')
        A.test2(self)


b = B()
b.test(False)
b.test2()
