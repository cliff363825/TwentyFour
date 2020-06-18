# coding: utf-8

import time, datetime


def date(format, timestamp=None, tz=None):
    if timestamp is None:
        timestamp = time.time()
    d = datetime.datetime.fromtimestamp(timestamp, tz)
    return d.strftime(format)


if __name__ == '__main__':
    import os

    # set the default timezone to use. Available since PHP 5.1
    os.environ['TZ'] = 'UTC'

    # Prints something like: Monday
    print(date("%A"))

    # Prints something like: Monday 8th of August 2005 03:12:46 PM
    print(date("%A %d of %B %Y %I:%M:%S %p"))

    # Prints: July 1, 2000 is on a Saturday
    # echo "July 1, 2000 is on a " . date("l", mktime(0, 0, 0, 7, 1, 2000)) . "\n";
    print("July 1, 2000 is on a " + date("%A", time.mktime((2000, 7, 1, 0, 0, 0, -1, -1, -1))))
    print(time.mktime((2000, 7, 1, 0, 0, 0, -1, -1, -1)))

    # /* use the constants in the format parameter */
    # prints something like: Wed, 25 Sep 2013 15:28:57 -0700
    print(date("%A, %d %b %Y %H:%M:%S %z", None, datetime.timezone.utc))

    # prints something like: 2000-07-01T00:00:00+00:00
    print(date("%Y-%m-%dT%H:%M:%S %z", time.mktime((2000, 7, 1, 0, 0, 0, -1, -1, -1)), datetime.timezone.utc))
