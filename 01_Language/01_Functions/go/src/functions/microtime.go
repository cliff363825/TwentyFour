package functions

import "time"

func Microtime() float64 {
	now := time.Now()
	return float64(now.Unix()) + float64(now.Nanosecond()) / 1000_000_000
}
