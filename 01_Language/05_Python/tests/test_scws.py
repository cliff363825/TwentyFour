# coding: utf-8
import json
from pprint import pprint
from urllib import parse

import re
import requests

title = "以下哪部超级英雄电影不属于漫威影业"
choices = ["金刚狼", "钢铁侠", "海王"]
pattern = r'[^\u4e00-\u9fa5_a-zA-Z0-9]'
counts = []
response = requests.get(url="http://api.pullword.com/get.php",
                        params=[
                            ("source", title),
                            ("param1", 0.9),
                            ("param2", 0),
                        ],
                        headers={"Connection": "close"})
content = response.text
word_list = [word.strip() for word in content.split("\n") if word.strip() != ""]
if len(word_list) > 0:
    response = requests.get(url="http://www.baidu.com/s", params=[('wd', ' '.join(word_list))],
                            headers={"Connection": "close"})
    content = response.text
    for i in range(len(choices)):
        choice = re.sub(pattern, "", choices[i])
        counts.append(content.count(choice))
    if "不是" in title:
        print('**请注意此题为否定题,选计数最少的**')
    for i in range(len(counts)):
        print("[题目+分词]\t%s\t%d" % (choices[i], counts[i]))
else:
    print("[题目+分词]\t获取失败")
