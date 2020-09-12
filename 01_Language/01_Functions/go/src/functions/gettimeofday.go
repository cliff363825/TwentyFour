package functions

import "time"

func Gettimeofday() map[string]interface{} {
	now := time.Now()
	_, offset := now.Zone()

	return map[string]interface{} {
		"sec": now.Unix(),
		"usec": now.Nanosecond() / 1000,
		"minuteswest": offset / -60,
		//"dsttime"
	}
}
