package functions

import (
	"time"
)

func DateIsodate(year int, week int, day time.Weekday) time.Time {
	first := time.Date(year, 1, 1, 0, 0, 0, 0, time.Local)
	days := int(day - first.Weekday())
	if day == time.Sunday {
		days += 7
	}
	if _, w := first.ISOWeek(); w != 1 {
		days += 7
	}
	days += 7 * (week - 1)
	return first.Add(time.Hour * 24 * time.Duration(days))
}
