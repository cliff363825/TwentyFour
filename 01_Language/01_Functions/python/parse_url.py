# coding: utf-8

import urllib.parse


def parse_url(url):
    return urllib.parse.urlparse(url)


if __name__ == '__main__':
    print(parse_url('http://username:password@hostname:9090/path?arg=value#anchor'))
