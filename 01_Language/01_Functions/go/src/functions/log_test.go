package functions

import (
	"fmt"
	"math"
	"testing"
)

func TestLog(t *testing.T) {
	fmt.Println(Log(1, math.E))
	fmt.Println(Log(2, 2))
}
