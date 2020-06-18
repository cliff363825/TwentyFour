# coding: utf-8

from decimal import Decimal

print((Decimal("1.34747474747") * Decimal("35")).quantize(Decimal(".001")))
print(Decimal("2") * Decimal("4"))
