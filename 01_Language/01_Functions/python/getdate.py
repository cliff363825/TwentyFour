# coding: utf-8
import datetime

_MONTHNAMES = [None, "January", "February", "March", "April", "May", "June",
               "July", "August", "September", "October", "November", "December"]
_DAYNAMES = [None, "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]


def getdate(ts=None):
    if ts is not None:
        d = datetime.datetime.fromtimestamp(ts)
    else:
        d = datetime.datetime.now()

    t = d.timetuple()
    res = {}
    res["seconds"] = d.second
    res["minutes"] = d.minute
    res["hours"] = d.hour
    res["mday"] = d.day
    res["wday"] = d.isoweekday()
    res["mon"] = d.month
    res["year"] = d.year
    res["yday"] = t.tm_yday - 1
    res["weekday"] = _DAYNAMES[d.isoweekday()]
    res["month"] = _MONTHNAMES[d.month]
    res[0] = int(d.timestamp())
    return res


if __name__ == '__main__':
    print(getdate())
