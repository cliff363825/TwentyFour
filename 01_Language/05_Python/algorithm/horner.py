# coding: utf-8

def horner(A=None, x=None):
    y = 0
    for i in range(len(A) - 1, -1, -1):
        y = A[i] + x * y
    return y


A = [1, 2, 3, 4]  # 1 + 2*x + 3*x**2 + 4 * x**3
print(horner(A, 2))
# print(list(range(10,0,-1)))  # [10, 0)
