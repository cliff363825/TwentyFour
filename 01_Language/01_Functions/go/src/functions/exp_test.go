package functions

import (
	"fmt"
	"math"
	"testing"
)

func TestExp(t *testing.T) {
	fmt.Println(math.Exp(12))
	fmt.Println(math.Exp(5.7))
}

func TestExpm1(t *testing.T) {
	fmt.Println(math.Expm1(0))
}
