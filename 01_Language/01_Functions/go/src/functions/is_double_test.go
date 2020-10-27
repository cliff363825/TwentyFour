package functions

import (
	"fmt"
	"testing"
)

func TestIsDouble(t *testing.T) {
	fmt.Println(IsDouble(27.25))
	fmt.Println(IsDouble("abc"))
	fmt.Println(IsDouble(23))
	fmt.Println(IsDouble(23.5))
	fmt.Println(IsDouble(1e7))
	fmt.Println(IsDouble(true))
}
