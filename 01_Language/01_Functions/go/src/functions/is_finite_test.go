package functions

import (
	"fmt"
	"math"
	"testing"
)

func TestIsFinite(t *testing.T) {
	fmt.Println(IsFinite(math.Sqrt(2)))
	fmt.Println(IsFinite(math.Log(0)))
	fmt.Println(IsFinite(math.Asin(2)))
}

