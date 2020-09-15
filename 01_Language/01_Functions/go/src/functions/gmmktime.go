package functions

import "time"

func Gmmktime(hour, minute, second, month, day, year int) int64 {
	return time.Date(year, time.Month(month), day, hour, minute, second, 0, time.UTC).Unix()
}
