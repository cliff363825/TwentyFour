# coding: utf-8

from decimal import Decimal

d1 = Decimal("5")
d2 = Decimal("3")
d3 = Decimal("-5")
d4 = Decimal("-3")

print(d1 % d2)
print(d1 % d4)
print(d3 % d2)
print(d3 % d4)

d5 = Decimal("5.7")
d6 = Decimal("1.3")
print(d5 % d6)
