# 在Python数值分成了三种：整数、浮点数（小数）、复数
# 在Python中所有的整数都是int类型
a = 10
b = 20
# Python中的整数的大小没有限制，可以是一个无限大的整数
# c = 999999999999999999999999999999999999999999999 ** 100

# 如果数字的长度过大，可以使用下划线作为分隔符
c = 123_456_789
print(c)

# d = 0123 10进制的数字不能以0开头
# 其他进制的整数，只要是数字打印时一定是以十进制的形式显示的
# 二进制 0b开头
c = 0b10  # 二进制的10
# 八进制 0o开头
c = 0o10
# 十六进制 0x开头
c = 0x10

# 也可以通过运算符来对数字进行运算，并且可以保证整数运算的精确
c = -100
c = c + 3

# 浮点数（小数），在Python中所有的小数都是float类型
c = 1.23
c = 4.56

# 对浮点数进行运算时，可能会得到一个不精确的结果
c = 0.1 + 0.2  # 0.30000000000000004

print(c)
