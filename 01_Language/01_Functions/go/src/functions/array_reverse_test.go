package functions

import (
	"fmt"
	"testing"
)

func TestArrayReverse(t *testing.T) {
	input := []interface{}{"php", 4.0, []string{"green", "red"}}
	for i, j := 0, len(input)-1; i < j; i, j = i+1, j-1 {
		input[i], input[j] = input[j], input[i]
	}
	fmt.Println(input)
}
