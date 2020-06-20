# coding: utf-8

import argparse
import json
import webbrowser
from threading import Thread
from time import sleep

import re
import requests
try:
    from urllib.parse import quote
except ImportError:
    from urllib import quote


def open_browser(url):
    """
    打开浏览器
    :type url: str
    """
    if url is not None and url.strip() != "":
        webbrowser.open(url=url, new=2)


def search_baidu(title, choices, mode=1, is_open=False):
    """
    百度搜索
    :type title: str
    :type choices: list[str]
    :type mode: int
    :type is_open: bool
    """
    counts = []
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36",
        "Connection": "close",
    }
    pattern = r'[^\u4e00-\u9fa5_a-zA-Z0-9]'
    title = re.sub(pattern, "", title)
    if mode == 1:
        response = requests.get(url="https://www.baidu.com/s", params=(("wd", title),), headers=headers)
        content = response.text
        for i in range(len(choices)):
            choice = re.sub(pattern, "", choices[i])
            counts.append(content.count(choice))
        if "不是" in title:
            print('**请注意此题为否定题,选计数最少的**')
        for i in range(len(counts)):
            print("[题目]\t%s\t%d" % (choices[i], counts[i]))
        if is_open:
            open_browser("https://www.baidu.com/s?wd=" + quote(title))
    elif mode == 2:
        count_pattern = re.compile(r'百度为您找到相关结果约([\d,]+)个', re.I | re.S | re.M | re.U)
        for i in range(len(choices)):
            choice = re.sub(pattern, "", choices[i])
            response = requests.get(url="https://www.baidu.com/s", params=(("wd", " ".join((title, choice))),),
                                    headers=headers)
            content = response.text
            match = count_pattern.search(content)
            if match is None:
                count = 0
            else:
                count = int(match.group(1).replace(',', ''))
            counts.append(count)
        if "不是" in title:
            print('**请注意此题为否定题,选计数最少的**')
        for i in range(len(counts)):
            print("[题目+选项]\t%s\t%d" % (choices[i], counts[i]))
        if is_open:
            open_browser("https://www.baidu.com/s?wd=" + quote(title))
    elif mode == 3:
        url_list = ["http://api.pullword.com/get.php", "http://43.241.223.121/get.php", "http://120.26.6.172/get.php"]
        word_list = []
        for url in url_list:
            try:
                response = requests.get(url=url,
                                        params=(
                                            ("source", title),
                                            ("param1", 0.9),
                                            ("param2", 0),
                                        ),
                                        headers=headers,
                                        timeout=1)
                content = response.text
                word_list = [word.strip() for word in content.split("\n") if word.strip() != ""]
                break
            except Exception:
                print("api url error: %s" % url)
                continue
        if len(word_list) > 0:
            response = requests.get(url="https://www.baidu.com/s", params=(("wd", " ".join(word_list)),),
                                    headers=headers)
            content = response.text
            for i in range(len(choices)):
                choice = re.sub(pattern, "", choices[i])
                counts.append(content.count(choice))
            if "不是" in title:
                print('**请注意此题为否定题,选计数最少的**')
            for i in range(len(counts)):
                print("[题目+分词]\t%s\t%d" % (choices[i], counts[i]))
            if is_open:
                open_browser("https://www.baidu.com/s?wd=" + quote(" ".join(word_list)))
        else:
            print("[题目+分词]\t获取失败")
            if is_open:
                open_browser("https://www.baidu.com/s?wd=" + quote(title))


def game_start(key):
    """

    :type key: str
    """
    title_list = []
    headers = {
        "User-Agent": "Mozilla/5.0 (Linux; Android 4.4.2; SM-G900F Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 SogouSearch Android1.0 version3.0 AppVersion/5909",
        "Referer": "http://nb.sa.sogou.com",
    }
    s = requests.session()
    error_times = 0
    while True:
        if error_times > 5:
            print("max error times reach: %d" % error_times)
            break
        try:
            response = s.get(url="http://140.143.49.31/api/ans2",
                             params=(
                                 ("key", key),
                                 ("wdcallback", "jQuery321029804591304030437_1516296495276"),
                             ),
                             headers=headers,
                             timeout=1)
            error_times = 0
        except Exception as e:
            print("request error: %s" % e)
            error_times = error_times + 1
            continue
        last_result_obj = None
        try:
            jsonp = response.content.decode("utf-8", errors="ignore")
            json_str = jsonp[jsonp.index("(") + 1:jsonp.rindex(")")]
            json_obj = json.loads(json_str)  # type: dict
            last_result_obj = json.loads(json_obj.get("result")[-1])
        except Exception as e:
            print("last result error: %s" % e)
        if last_result_obj is not None:
            title = last_result_obj.get("title").strip()
            if title != "" and title not in title_list:
                title_list.append(title)
                choices = last_result_obj.get("answers", [])  # type: list[str]
                result = last_result_obj.get("result")
                try:
                    choice = ["A", "B", "C"][choices.index(result)]
                except (ValueError, IndexError):
                    choice = ""
                error = last_result_obj.get("error")
                if error is not None and error == 1:
                    error_str = "**答案不确定**"
                else:
                    error_str = ""
                print("---------------------")
                print("[Q] %s\n[%s] %s %s" % (title, choice, result, error_str))
                print("---------------------")
                # url = ""
                # for info in last_result_obj.get("search_infos", []):
                #     url = info.get("url")
                #     break
                # t1 = Thread(target=open_browser, args=(url,))
                t2 = Thread(target=search_baidu, args=(title, choices, 1))
                t3 = Thread(target=search_baidu, args=(title, choices, 3))
                # t1.start()
                t2.start()
                t3.start()
        sleep(0.5)
    s.close()


if __name__ == "__main__":
    # parser = argparse.ArgumentParser(description="汪仔答题助手脚本")
    # parser.add_argument("-k", "--key", type=str, help="答题APP KEY", required=True,
    #                     choices=['xigua', 'huajiao', 'cddh', 'zscr'])
    # args = parser.parse_args()
    # game_start(args.key)
    game_start("cddh")
