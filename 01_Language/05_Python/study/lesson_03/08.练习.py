# 水仙花数是指一个 n 位数（n≥3 ），它的每个位上的数字的 n 次幂之和等于它本身（例如：1**3 + 5**3 + 3**3 = 153）。
# 求1000以内所有的水仙花数

# 获取1000以内的三位数
i = 100
while i < 1000:

    # 假设，i的百位数是a，十位数b，个位数c
    # 求i的百位数
    a = i // 100
    # 求i的十位数
    # b = i // 10 % 10
    b = (i - a * 100) // 10
    # 求i的个位数字
    c = i % 10
    # print(i , a , b , c)

    # 判断i是否是水仙花数
    if a ** 3 + b ** 3 + c ** 3 == i:
        print(i)
    i += 1
