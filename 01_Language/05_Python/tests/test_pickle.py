# coding: utf-8
import pickle

d = dict(name='Bob', age=20, score=88)
print(pickle.dumps(d))
# f = open('pickle.txt', 'wb')
# pickle.dump(d, f)
# f.close()

f = open('pickle.txt', 'rb')
o = pickle.load(f)
f.close()
print(o)
