package functions

import (
	"fmt"
	"math"
	"testing"
)

func TestIsInfinite(t *testing.T) {
	fmt.Println(IsInfinite(math.Sqrt(2)))
	fmt.Println(IsInfinite(math.Log(0)))
	fmt.Println(IsInfinite(math.Asin(2)))
}

