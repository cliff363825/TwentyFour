# coding: utf-8

import math

func = lambda a, b: "ln(a) + ln(b) = " + str(math.log(a * b))
print("New anonymous function: " + repr(func))
print(func(2, math.e))
