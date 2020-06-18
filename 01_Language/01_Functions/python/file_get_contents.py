# coding: utf-8

import requests


def file_get_contents(filename, use_include_path=False, context=None, offset=None, maxlen=None):
    if filename.lower().startswith("http://") or filename.lower().startswith("https://"):
        resp = requests.get(filename)
        content = resp.content
        if offset is not None:
            content = content[offset:]
        if maxlen is not None:
            content = content[:maxlen]
        return content.decode("utf-8", errors="ignore")
    else:
        with open(filename) as f:
            return f.read()


if __name__ == '__main__':
    print(file_get_contents("https://www.onevgo.com"))
