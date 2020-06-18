# coding: utf-8

from decimal import Decimal

d1 = Decimal("1")
d2 = Decimal("2")
d3 = Decimal("1.00001")
print(d1.compare(d2))
print(d3.quantize(Decimal(".0001")).compare(d1))
print(d3.quantize(Decimal(".000001")).compare(d1))
