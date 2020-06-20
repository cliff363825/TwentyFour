# coding: utf-8

from enum import Enum, unique

Month = Enum('Month', ('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'))

for k, v in Month.__members__.items():
    print(k, '=>', v, '=>', v.value)


@unique
class Weekday(Enum):
    Sun = 0
    Mon = 1
    Tue = 2
    Wed = 3
    Thu = 4
    Fri = 5
    Sat = 6


day1 = Weekday.Mon
# assert day1 == Weekday.Mon, 'day1 is not monday'
# assert day1 == Weekday.Tue, 'day1 is not tuesday'
print(day1)
print(day1.value)
print(Weekday(0))
print(Weekday(1))
