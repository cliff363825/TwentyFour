package functions

import (
	"fmt"
	"testing"
)

func TestIsBool(t *testing.T) {
	var a bool = false
	var b int = 0
	fmt.Println(IsBool(a))
	fmt.Println(IsBool(b))
}

