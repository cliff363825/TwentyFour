# coding: utf-8

import logging

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)-8s %(filename)s:%(lineno)-4d: %(message)s',
                    datefmt='%Y-%m-%d %H:%M:%S')

import requests
import json
import sys
import re
import os

# 内网服务地址
url = 'http://finance.internal.com/api/wx/send-msg'


def main():
    try:
        user_id = sys.argv[1]
        content = sys.argv[2]
    except IndexError:
        print 'Usage: your_python ' + os.path.basename(__file__) + ' xiaozi msg'
        return

    # 发送数据
    data = {
        'user_id': user_id,  # 发送用户，多个用户用英文逗号分隔
        'content': content,  # 发送内容
    }
    logging.info('请求数据 => %s', json.dumps(data, ensure_ascii=False))

    res = requests.post(url=url, data=json.dumps(data),
                        headers={'Content-Type': 'application/json'})
    logging.info('请求结果 => %s', res.content)


if __name__ == '__main__':
    sys.argv[0] = re.sub(r'(-script\.pyw?|\.exe)?$', '', sys.argv[0])
    sys.exit(main())
