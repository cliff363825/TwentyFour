# coding: utf-8

import requests

# d = dict()
# d['headers'] = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36'}
# r = requests.get('https://www.douban.com/', **d)
# print(r, type(r), dir(r), sep="\n")
# # for i in iter(r):
# #     print(i.decode('utf-8', errors='ignore'))
# print(r.status_code)
# print(r.text)

# r = requests.get('https://www.douban.com/search', params=[('q', 'python'), ('cat', '1001')])
# print(r.url, r.encoding)
# print(r.content)

# r = requests.get('https://query.yahooapis.com/v1/public/yql', params=[('q', 'select * from weather.forecast where woeid=202151330'), ('format', 'json')])
# print(r.json())

# r = requests.get('https://www.douban.com/', headers={'User-Agent': 'Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit'})
# print(r.text)

# r = requests.post('https://accounts.douban.com/login', data=[('form_email', 'abc@example.com'), ('form_password', '123456')])
# print(r.text)

# params = dict([('key', 'value')])
url = 'https://www.onevgo.com'
# r = requests.post(url, json=params)
# print(r.text)

upload_files = dict([('file', open('test.jpg', 'rb'))])
r = requests.post(url, files=upload_files)
print(r.headers, r.headers['Content-Type'])
# print(dir(r.headers))
