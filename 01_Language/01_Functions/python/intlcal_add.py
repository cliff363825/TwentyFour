# coding: utf-8

import datetime
from dateutil.relativedelta import relativedelta
import pytz

if __name__ == '__main__':
    d = datetime.datetime(2012, 1, 31, tzinfo=pytz.timezone("GMT"))
    print(d.strftime("%Y-%m-%d %H:%M:%S %z"))

    d = d + relativedelta(months=1)
    print(d.strftime("%Y-%m-%d %H:%M:%S %z"))

    d = d + relativedelta(days=1)
    print(d.strftime("%Y-%m-%d %H:%M:%S %z"))
