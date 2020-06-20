# coding: utf-8

class A(object):
    def __init__(self):
        print("A before")
        super(A, self).__init__()
        print("A after")


class B(A):
    def __init__(self):
        print("B before")
        super(B, self).__init__()
        print("B after")


class C(A):
    def __init__(self):
        print("C before")
        super(C, self).__init__()
        print("C after")


class D(B):
    def __init__(self):
        print("D before")
        super(D, self).__init__()
        print("D after")


# F C D B A object
class F(C, D):
    def __init__(self):
        print("F before")
        super(F, self).__init__()
        print("F after")


f = F()
print(f.__class__.mro())
print(F.mro())
