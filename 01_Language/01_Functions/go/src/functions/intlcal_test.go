package functions

import (
	"fmt"
	"testing"
	"time"
)

func TestIntlcalAdd(t *testing.T) {
	loc, _ := time.LoadLocation("UTC")

	date := time.Date(2012, time.Month(1), 31, 0, 0, 0, 0, loc)
	fmt.Println(date.Format("2006-01-02 15:04:05 -0700"))

	// 2012-03-02 00:00:00 +0000??
	date1 := date.AddDate(0, 1, 0)
	fmt.Println(date1)
}
