package functions

import (
	"fmt"
	"testing"
	"time"
)

func TestGmstrftime(t *testing.T) {
	fmt.Println(Gmstrftime("2006-01-02 15:04:05-07:00", time.Date(1998, 12, 31, 20, 0, 0, 0, time.Local)))
}

