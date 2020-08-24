package functions

import "time"

func Getdate(t *time.Time) map[string]interface{} {
	m := make(map[string]interface{})
	m["seconds"] = t.Second()
	m["minutes"] = t.Minute()
	m["hours"] = t.Hour()
	m["mday"] = t.Day()
	m["wday"] = int(t.Weekday())
	m["mon"] = int(t.Month())
	m["year"] = t.Year()
	m["yday"] = t.YearDay() - 1
	m["weekday"] = t.Weekday().String()
	m["month"] = t.Month().String()
	m["0"] = t.Unix()
	return m
}
