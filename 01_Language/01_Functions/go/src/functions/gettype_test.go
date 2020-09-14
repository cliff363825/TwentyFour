package functions

import (
	"fmt"
	"testing"
)

func TestGettype(t *testing.T) {
	var s = struct {
	}{}
	var sp = &s
	var arr = []interface{}{1, 1., nil, s, sp, "foo"}
	for _, v := range arr {
		fmt.Println(Gettype(v))
	}
}

