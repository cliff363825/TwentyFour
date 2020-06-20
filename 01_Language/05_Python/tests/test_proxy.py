# coding: utf-8
import requests

proxies = {
    'http': 'socks5://127.0.0.1:1080',
    'https': 'socks5h://127.0.0.1:1080'
}
response = requests.get(url="https://www.google.com.hk", proxies=proxies, headers={"accept-language": "zh-CN,zh;q=0.9"})
print(response.text)
