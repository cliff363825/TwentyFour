# coding: utf-8

import os
import subprocess

# print(os.system("whoami"))
print(subprocess.call("whoami", shell=True))
print(subprocess.run("whoami", shell=True))
print(subprocess.run("howareyou", shell=True))
