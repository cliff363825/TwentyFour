# coding: utf-8

import re

def preg_match_all(pattern, subject):
    p = re.compile(pattern)
    return p.finditer(subject)

if __name__ == '__main__':
    print(list(preg_match_all(r'\(?(?:(\d{3})-)?\)?\d{3}-\d{4}', 'Call 555-1212 or 1-800-555-1212')))