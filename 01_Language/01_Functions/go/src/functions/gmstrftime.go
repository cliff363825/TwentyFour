package functions

import "time"

func Gmstrftime(format string, t time.Time) string {
	return t.UTC().Format(format)
}
