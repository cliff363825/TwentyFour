# coding: utf-8

import os
import stat

# Read and write for owner, nothing for everybody else
os.chmod("test.txt", 0o0600)

# Read and write for owner, read for everybody else
os.chmod("test.txt", 0o0644)

# Everything for owner, read and execute for others
os.chmod("test.txt", 0o0755)

# Everything for owner, read and execute for owner's group
os.chmod("test.txt", 0o0750)
