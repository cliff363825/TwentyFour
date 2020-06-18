# coding: utf-8

import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect(("www.onevgo.com", 80))
    content = "GET / HTTP/1.1\r\n" + \
              "Host: www.onevgo.com\r\n" + \
              "Connection: Close\r\n\r\n"
    # s.sendall(content.encode())
    # resp = s.recv(1024)

    fd = s.makefile(mode="rw")
    # fd = open("")
    fd.write(content)
    fd.flush()
    resp = fd.read(1024)

    print(resp)
