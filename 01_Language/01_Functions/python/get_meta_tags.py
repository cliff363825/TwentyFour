# coding: utf-8

import requests
import re

_pattern = re.compile(r'<meta(?=[^>]*name=([\'"]?)(?P<name>.*?)\1)(?=[^>]*content=([\'"]?)(?P<content>.*?)\3).*?>',
                      re.I)


def get_meta_tags(url):
    content = requests.get(url).content.decode("utf-8", errors="ignore")
    m = _pattern.findall(content)
    return dict((i[1], i[3]) for i in m)


if __name__ == '__main__':
    print(get_meta_tags("https://www.onevgo.com"))
