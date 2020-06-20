# coding: utf-8

from collections import namedtuple, deque, defaultdict, OrderedDict, Counter

# namedtuple
Point = namedtuple('Point', ['x', 'y'])
p = Point(1, 2)
print(type(p), p, p.x, p.y)

Circle = namedtuple('Circle', ['x', 'y', 'r'])
c = Circle(0, 0, 2)
print(type(c), c, c.x, c.y, c.r)

# deque
q = deque(['a', 'b', 'c'])
q.append('x')
q.appendleft('y')
print(type(q), q, q[0])

# defaultdict
dd = defaultdict(lambda: 'N/A')
dd['key1'] = 'abc'
print(dd['key1'], dd['key2'])

# OrderedDict
od = OrderedDict([('a', 1), ('b', 2), ('c', 3)])
print(od)

# Counter
c = Counter()
for ch in 'programming':
    c[ch] = c[ch] + 1
print(c)

# dict
d = dict([('a', 1), ('b', 2), ('c', 3)])
# d[(1, [2, 3])] = 4 # raise TypeError: unhashable type: 'list'
# dict的遍历
# 1. dict.__iter__()
for k in d:
    print(k, '=', d[k])
# 2. dict.keys()
for k in d.keys():
    print(k, '=', d[k])
# 3. dict.values()
for v in d.values():
    print(v)
# 4. dict.items()
for k, v in d.items():
    print(k, '=', v)

# set
s = set(['a', 'b', 'c', 'a'])
# set的遍历
# 1. set.__iter__()
for i in s:
    print(i)
# 2. enumerate
for k, v in enumerate(s):
    print(k, '=', v)

# list
classmeates = ['Michael', 'Bob', 'Tracy']
print(classmeates, len(classmeates))
print(classmeates[0], classmeates[1], classmeates[2])
# print(classmeates[3])
print(classmeates[-1], classmeates[-2], classmeates[-3])
# print(classmeates[-4])

classmeates.append('Adam')
print(classmeates, len(classmeates))

classmeates.insert(1, 'Jack')
print(classmeates, len(classmeates))

print(classmeates.pop())
print(classmeates, len(classmeates))
print(classmeates.pop(1))
print(classmeates, len(classmeates))

classmeates[1] = 'Sarah'
print(classmeates, len(classmeates))

classmeates.extend([1, 2, 3])
print(classmeates, len(classmeates))
# list的遍历
# 1. list.__iter__()
for i in classmeates:
    print(i)
# 2. enumerate
for k, v in enumerate(classmeates):
    print(k, '=', v)
