package functions

import (
	"fmt"
	"math"
	"testing"
)

func TestFloor(t *testing.T) {
	fmt.Println(math.Floor(4.3))
	fmt.Println(math.Floor(9.999))
	fmt.Println(math.Floor(-3.14))
}
