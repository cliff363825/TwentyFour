# coding: utf-8

import time

_MONTHNAMES = [None, "January", "February", "March", "April", "May", "June",
               "July", "August", "September", "October", "November", "December"]
_DAYNAMES = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]


def getdate(timestamp=None):
    if timestamp is None:
        timestamp = time.time()
    t = time.localtime(timestamp)

    res = {}
    res["seconds"] = t.tm_sec
    res["minutes"] = t.tm_min
    res["hours"] = t.tm_hour
    res["mday"] = t.tm_mday
    res["wday"] = t.tm_wday
    res["mon"] = t.tm_mon
    res["year"] = t.tm_year
    res["yday"] = t.tm_yday - 1
    res["weekday"] = _DAYNAMES[t.tm_wday]
    res["month"] = _MONTHNAMES[t.tm_mon]
    res[0] = timestamp
    return res


if __name__ == '__main__':
    print(getdate())
