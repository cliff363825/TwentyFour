# coding: utf-8

from decimal import Decimal, ROUND_FLOOR

print((Decimal("4.2") ** 3).quantize(Decimal(".01"), ROUND_FLOOR))
