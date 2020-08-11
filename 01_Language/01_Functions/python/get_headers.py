# coding: utf-8

import requests


def get_headers(url):
    return requests.get(url, allow_redirects=False).headers


if __name__ == '__main__':
    print(get_headers("http://www.onevgo.com"))
