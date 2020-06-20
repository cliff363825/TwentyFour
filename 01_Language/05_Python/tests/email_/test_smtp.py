# coding: utf-8

from email import encoders
from email.header import Header
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.utils import parseaddr, formataddr

import smtplib


def _format_addr(s):
    name, addr = parseaddr(s)
    return formataddr((Header(name, 'utf-8').encode(), addr))


# from_addr = input('From: ')
from_addr = '176462329@qq.com'
# password = input('Password: ')
password = ''
# to_addr = input('To: ')
to_addr = from_addr
# smtp_server = input('SMTP server: ')

# 发送纯文本邮件
# msg = MIMEText('hello, send by Python...', 'plain', 'utf-8')
# 发送HTML邮件
# msg = MIMEText('<html><body><h1>Hello</h1>' +
#     '<p>send by <a href="http://www.python.org">Python</a>...</p>' +
#     '</body></html>', 'html', 'utf-8')
# 发送附件
msg = MIMEMultipart()
# 同时支持HTML和Plain格式
# msg = MIMEMultipart('alternative')
msg['From'] = _format_addr('Python爱好者 <%s>' % from_addr)
msg['To'] = _format_addr('管理员 <%s>' % to_addr)
msg['Subject'] = Header('来自SMTP的问候。。。', 'utf-8').encode()

# msg.attach(MIMEText('send with file...', 'plain', 'utf-8'))
# 图片嵌入到邮件正文 cid:x
msg.attach(MIMEText('<html><body><h1>Hello</h1>' +
                    '<p><img src="cid:0"></p>' +
                    '</body></html>', 'html', 'utf-8'))

# 添加附件就是加上一个MIMEBase, 从本地读取一个图片
with open('../test.jpg', 'rb') as f:
    # 设置附件的MIME和文件名，这里是PNG类型
    mime = MIMEBase('image', 'jpg', filename='test.jpg')
    # 加上必要的头信息
    mime.add_header('Content-Disposition', 'attachment', filename='test.jpg')
    mime.add_header('Content-ID', '<0>')
    mime.add_header('X-Attachment-Id', '0')
    # 把附件的内容读进来
    mime.set_payload(f.read())
    # 用Base64编码
    encoders.encode_base64(mime)
    # 添加到MIMEMultipart
    msg.attach(mime)

server = smtplib.SMTP('smtp.qq.com', 25)
server.starttls()
# server = smtplib.SMTP_SSL('smtp.qq.com', 465)
server.set_debuglevel(1)
server.login(from_addr, password)
server.sendmail(from_addr, [to_addr], msg.as_string())
server.quit()
