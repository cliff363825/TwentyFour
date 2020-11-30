package functions

import (
	"fmt"
	"testing"
	"time"
)

func TestLocaltime(t *testing.T) {
	fmt.Println(Localtime(time.Now()))
}

