# coding: utf-8

from decimal import Decimal

print(Decimal("2").sqrt().quantize(Decimal(".001")))
