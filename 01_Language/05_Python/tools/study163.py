# coding: utf-8

"""
获取网易云课堂免费课程
"""

import requests
import json
import re
from pprint import pprint

def get_course_list(l, params):
    resp = requests.post('http://study.163.com/p/search/studycourse.json', json=params)
    data = json.loads(resp.content.decode('utf-8', errors='ignore'))
    for i in data['result']['list']:
        l.append([
            i['productName'],
            i['provider'],
            i['learnerCount'] or 0,
            'http://study.163.com/course/introduction/' + str(i['productId']) + '.htm'
        ])
    if params['pageIndex'] < data['result']['query']['totlePageCount']:
        params['pageIndex'] += 1
        params['relativeOffset'] = (params['pageIndex'] - 1) * params['pageSize']
        get_course_list(l, params)

if __name__ == '__main__':
    study_163_url = 'http://study.163.com/category/c'
    resp = requests.get(study_163_url)
    html = resp.content.decode('utf-8', errors='ignore')
    pattern = re.compile('window\.categoryId\s*=\s*"(\d+)"')
    match = pattern.search(html)
    if match:
        frontCategoryId = match.group(1)
    else:
        frontCategoryId = ''

    # {"pageIndex":1,"pageSize":50,"relativeOffset":0,"frontCategoryId":"400000000146054","searchTimeType":-1,"orderType":90,"priceType":-1,"activityId":0}
    post_params = dict([
        ('pageIndex', 1),
        ('pageSize', 50),
        ('relativeOffset', 0),
        ('frontCategoryId', frontCategoryId),
        ('searchTimeType', -1),
        ('orderType', 90),
        ('priceType', 0),
        ('activityId', 0),
    ])

    l = []
    get_course_list(l, post_params)
    l.sort(key=lambda x: int(x[2]), reverse=True)
    for i in l:
        print('\t'.join([str(x) for x in i]))
