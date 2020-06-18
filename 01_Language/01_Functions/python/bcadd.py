# coding: utf-8

from decimal import Decimal

d1 = Decimal("1.2345")
d2 = Decimal("5")
print(d1 + d2)
print((d1 + d2).quantize(Decimal("0.001")))
