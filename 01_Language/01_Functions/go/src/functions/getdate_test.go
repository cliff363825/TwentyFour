package functions

import (
	"fmt"
	"testing"
	"time"
)

func TestGetdate(t *testing.T) {
	now := time.Now()
	fmt.Println(Getdate(&now))
}

