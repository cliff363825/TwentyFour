# coding: utf-8

import traceback

f = open("test.txt", mode="r")
try:
    print(f.read())
except Exception as e:
    traceback.print_exc()
finally:
    f.close()
    print("Close!")
# with open("test.txt", mode="r") as f:
#     print(f.read())
