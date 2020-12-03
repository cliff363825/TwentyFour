# coding: utf-8

import smtplib

msg = "Line 1\nLine 2\nLine 3"
server = smtplib.SMTP('localhost')
server.sendmail('sender@example.com', 'caffeinated@example.com', msg)
server.quit()
