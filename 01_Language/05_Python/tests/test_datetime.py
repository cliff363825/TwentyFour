# coding: utf-8

from datetime import datetime, timedelta, timezone

now = datetime.now()
print(type(now), now)
utc_now = datetime.utcnow()
print(type(utc_now), utc_now)
print(now + timedelta(hours=10))
print(now - timedelta(days=1), now + timedelta(days=-1))
print(now + timedelta(days=2, hours=12))

dt = datetime(2018, 3, 7, 19, 43)
print(type(dt), dt)

print(datetime.fromtimestamp(1234567890), datetime.utcfromtimestamp(1234567890))

dt = datetime.strptime('2018-3-7 19:53:00', '%Y-%m-%d %H:%M:%S')
print(type(dt), dt)
dt_str = datetime.now().strftime('%a, %b %d %H:%M')
print(type(dt_str), dt_str)

tz_utc_8 = timezone(timedelta(hours=8))
dt = now.replace(tzinfo=tz_utc_8)
print(type(dt), dt)  # 2018-03-08 19:39:05.194095+08:00

utc_dt = datetime.utcnow().replace(tzinfo=timezone.utc)
bj_dt = utc_dt.astimezone(timezone(timedelta(hours=8)))
print(bj_dt)
tokyo_dt = utc_dt.astimezone(timezone(timedelta(hours=9)))
print(tokyo_dt)
tokyo_dt = bj_dt.astimezone(timezone(timedelta(hours=9)))
print(tokyo_dt)
