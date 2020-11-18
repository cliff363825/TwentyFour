package functions

import (
	"fmt"
	"testing"
)

func TestJsonDecode(t *testing.T) {
	var s = "{\"a\":1,\"b\":2,\"c\":3,\"d\":4,\"e\":5}"
	var v map[string]int
	e := JsonDecode(s, &v)
	fmt.Println(e, v)
}
