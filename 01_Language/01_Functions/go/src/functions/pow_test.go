package functions

import (
	"fmt"
	"math"
	"testing"
)

func TestPow(t *testing.T) {
	fmt.Println(math.Pow(2, 8))
	fmt.Println(math.Pow(-1, 5.5))
}

