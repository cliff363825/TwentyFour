package functions

import "time"

func Mktime(hour, minute, second, month, day, year int) int64 {
	return time.Date(year, time.Month(month), day, hour, minute, second, 0, time.Local).Unix()
}
