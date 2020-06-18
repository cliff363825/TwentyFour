package functions

import "time"

func Date(format string, t time.Time) string {
	// Mon Jan 2 15:04:05 -0700 MST 2006
	return t.Format(format)
}
