package functions

import (
	"fmt"
	"testing"
	"time"
)

func TestGmdate(t *testing.T) {
	fmt.Println(Date("2006-01-02 15:04:05", time.Now()))
	fmt.Println(Gmdate("2006-01-02 15:04:05", time.Now()))
}

