package functions

import "time"

func Gmdate(format string, time time.Time) string {
	return time.UTC().Format(format)
}
