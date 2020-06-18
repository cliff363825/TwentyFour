# coding: utf-8

from decimal import Decimal

d1 = Decimal("105")
d2 = Decimal("6.55957")
print((d1 / d2).quantize(Decimal(".001")))
