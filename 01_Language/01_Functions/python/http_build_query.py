# coding: utf-8

import urllib.parse

def http_build_query(query_data):
    return urllib.parse.urlencode(query_data)

if __name__ == '__main__':
    d = {
        "foo" : "bar",
        "baz" : "boom",
        "cow" : "milk",
        "php" : "hypertext processor",
        0 : ["a", "b", "c"],
    }
    print(http_build_query(d))