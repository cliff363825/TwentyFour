package functions

import (
	"fmt"
	"testing"
)

func TestParseStr(t *testing.T) {
	values, _ := ParseStr("first=value&arr[]=foo+bar&arr[]=baz")
	fmt.Println(values)
}

