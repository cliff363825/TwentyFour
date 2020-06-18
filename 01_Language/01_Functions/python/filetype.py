# coding: utf-8
import os
import stat


def filetype(filename):
    st = os.stat(filename, follow_symlinks=False)
    mode = st.st_mode
    if stat.S_ISFIFO(mode):
        return "fifo"
    elif stat.S_ISCHR(mode):
        return "char"
    elif stat.S_ISDIR(mode):
        return "dir"
    elif stat.S_ISBLK(mode):
        return "block"
    elif stat.S_ISLNK(mode):
        return "link"
    elif stat.S_ISSOCK(mode):
        return "socket"
    elif stat.S_ISREG(mode):
        return "file"
    else:
        return "unknown"


if __name__ == '__main__':
    print(filetype("/dev/urandom"))
    print(filetype("/dev/"))
    print(filetype("/dev/disk0"))
    print(filetype("/dev/stdin"))
    print(filetype("/etc/passwd"))
    print(filetype("/var/run/syslog"))
