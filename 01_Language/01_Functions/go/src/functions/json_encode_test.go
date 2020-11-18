package functions

import (
	"fmt"
	"testing"
)

func TestJsonEncode(t *testing.T) {
	var a = map[string]int{
		"a": 1,
		"b": 2,
		"c": 3,
		"d": 4,
		"e": 5,
	}
	fmt.Println(JsonEncode(a))
}
