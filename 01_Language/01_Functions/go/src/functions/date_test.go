package functions

import (
	"fmt"
	"testing"
	"time"
)

func TestDate(t *testing.T) {
	// set the default timezone to use. Available since PHP 5.1
	loc, _ := time.LoadLocation("UTC")

	// Prints something like: Monday
	fmt.Println(Date("Monday", time.Now().In(loc)))
	//fmt.Println(time.Now().In(loc))

	// Prints something like: Monday 8th of August 2005 03:12:46 PM
	fmt.Println(Date("Monday 2 of January 2006 03:04:05 PM", time.Now().In(loc)))

	// Prints: July 1, 2000 is on a Saturday
	time1, _ := time.Parse("2006-01-02 15:04:05", "2000-07-01 00:00:00")
	fmt.Println("July 1, 2000 is on a " + Date("Monday", time1.In(loc)))

	// /* use the constants in the format parameter */
	// prints something like: Wed, 25 Sep 2013 15:28:57 -0700
	fmt.Println(Date(time.RFC1123Z, time.Now().In(loc)))

	// prints something like: 2000-07-01T00:00:00+00:00
	fmt.Println(Date("2006-01-02T15:04:05-07:00", time1.In(loc)))
}

func TestDateAdd(t *testing.T) {
	d, _ := time.Parse("2006-01-02", "2000-01-01")
	d = d.AddDate(0, 0, 10)
	fmt.Println(d.Format("2006-01-02"))
}

func TestDateCreate(t *testing.T) {
	d := time.Date(2000, 1, 1, 0, 0, 0, 0, time.Local)
	fmt.Println(d.Format("2006-01-02"))
}

func TestDateCreateFromFormat(t *testing.T) {
	d, _ := time.Parse("2-Jan-2006", "15-Feb-2009")
	fmt.Println(d.Format("2006-01-02"))
}

func TestDateDateSet(t *testing.T) {
	d := time.Now()
	d = time.Date(2001, 2, 3, d.Hour(), d.Minute(), d.Second(), d.Nanosecond(), d.Location())
	fmt.Println(d.Format("2006-01-02"))
}

func TestDateDefaultTimezoneGet(t *testing.T) {
	fmt.Println(time.Now().Zone())
}

func TestDateDefaultTimezoneSet(t *testing.T) {
	fmt.Println(time.Now().Format("2006-01-02 15:04:05 MST -0700"))
	loc, _ := time.LoadLocation("America/Los_Angeles")
	fmt.Println(time.Now().In(loc).Format("2006-01-02 15:04:05 MST -0700"))
}

func TestDateDiff(t *testing.T) {
	d1, _ := time.Parse("2006-01-02", "2009-10-11")
	d2, _ := time.Parse("2006-01-02", "2009-10-13")
	duration := d2.Sub(d1)
	fmt.Println(duration.Hours())
	fmt.Println(duration.Minutes())
	fmt.Println(duration.Seconds())
}

func TestDateFormat(t *testing.T) {
	d, _ := time.Parse("2006-01-02", "2000-01-01")
	fmt.Println(d.Format("2006-01-02 15:04:05"))
}

func TestDateIntervalCreateFromDateString(t *testing.T) {
	fmt.Println(time.ParseDuration("24h"))
	fmt.Println(time.ParseDuration("3600s"))
}

func TestDateIsodate(t *testing.T) {
	fmt.Println(DateIsodate(2008, 2, time.Monday))
	fmt.Println(DateIsodate(2008, 2, time.Sunday))
	for i := 1970; i < 2021; i++ {
		d := time.Date(i, 1, 1, 0, 0, 0, 0, time.Local)
		year, week := d.ISOWeek()
		fmt.Println(i, year, week, d.Weekday())
	}
}

func TestDateModify(t *testing.T) {
	d, _ := time.Parse("2006-01-02", "2006-12-12")
	d = d.Add(time.Hour * 24)
	fmt.Println(d.Format("2006-01-02"))
}

func TestDateOffsetGet(t *testing.T) {
	loc, _ := time.LoadLocation("America/New_York")
	winter, _ := time.Parse("2006-01-02", "2010-12-21")
	summer, _ := time.Parse("2006-01-02", "2008-06-21")
	fmt.Println(winter.In(loc).Zone())
	fmt.Println(summer.In(loc).Zone())
}

func TestDateParse(t *testing.T) {
	d, _ := time.Parse("2006-01-02 15:04:05.999", "2006-12-12 10:00:00.5")
	fmt.Println(d)
	fmt.Println(d.UnixNano())
}

func TestDateParseFromString(t *testing.T) {
	d, _ := time.Parse("2006-01-02 15:04:05.999", "2006-12-12 10:00:00.5")
	fmt.Println(d)
	fmt.Println(d.UnixNano())
}

func TestDateSub(t *testing.T) {
	d, _ := time.Parse("2006-01-02", "2009-01-20")
	d = d.Add(-time.Hour * 24 * 10)
	fmt.Println(d.Format("2006-01-02"))
}

func TestDateTimeSet(t *testing.T) {
	d, _ := time.Parse("2006-01-02", "2001-01-01")
	d = time.Date(d.Year(), d.Month(), d.Day(), 14, 55, d.Second(), d.Nanosecond(), d.Location())
	fmt.Println(d.Format("2006-01-02 15:04:05"))
}

func TestDateTimestampGet(t *testing.T) {
	d := time.Now()
	fmt.Println(d.Unix())
}

func TestDateTimestampSet(t *testing.T) {
	d := time.Unix(1171502725, 0)
	fmt.Println(d.Format("2006-01-02 15:04:05"))
}

func TestDateTimeZoneGet(t *testing.T) {
	d := time.Now()
	fmt.Println(d.Zone())
}

func TestDateTimeZoneSet(t *testing.T) {
	loc, _ := time.LoadLocation("Pacific/Nauru")
	d := time.Date(2000, 1, 1, 0, 0, 0, 0, loc)
	fmt.Println(d)
	loc, _ = time.LoadLocation("Pacific/Chatham")
	d = d.In(loc)
	fmt.Println(d)
}


