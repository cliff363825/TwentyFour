package functions

import (
	"fmt"
	"testing"
)

func TestArrayCombine(t *testing.T) {
	a := []string{"green", "red", "yellow"}
	b := []string{"avocado", "apple", "banana"}

	// convert []string to []interface{}
	c := make([]interface{}, len(a))
	for i := range a {
		c[i] = a[i]
	}
	d := make([]interface{}, len(b))
	for i := range b {
		d[i] = b[i]
	}

	fmt.Println(ArrayCombine(c, d))
}
