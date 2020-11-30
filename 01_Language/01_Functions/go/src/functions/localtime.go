package functions

import "time"

func Localtime(t time.Time) map[string]int {
	result := make(map[string]int)
	result["tm_sec"] = t.Second()
	result["tm_min"] = t.Minute()
	result["tm_hour"] = t.Hour()
	result["tm_mday"] = t.Day()
	result["tm_mon"] = int(t.Month())
	result["tm_year"] = t.Year()
	result["tm_wday"] = int(t.Weekday())
	result["tm_yday"] = t.YearDay()
	// result["tm_isdst"] = 0
	return result
}
