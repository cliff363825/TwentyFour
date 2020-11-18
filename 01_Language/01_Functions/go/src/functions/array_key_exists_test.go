package functions

import (
	"fmt"
	"testing"
)

func TestArrayKeyExists(t *testing.T) {
	var search = map[string]interface{} {
		"first": 1,
		"second": 4,
		"": false,
	}
	if _, ok := search["first"]; ok {
		fmt.Println("The 'first' element is in the array")
	}

	if _, ok := search["0"]; ok {
		fmt.Println("The '0' element is in the array")
	}
}

