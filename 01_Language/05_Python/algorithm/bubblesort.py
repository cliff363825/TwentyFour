# coding: utf-8

def bubblesort(A=None):
    for i in range(0, len(A) - 1):  # i in [0, len-1)
        for j in range(i + 1, len(A)):  # j in [i+1, len)
            if A[i] > A[j]:
                A[i], A[j] = A[j], A[i]


A = [1, 3, 5, 7, 9, 8, 6, 4, 2, 0]
print("start bubble %s" % A)
bubblesort(A)
print("end bubble %s" % A)
