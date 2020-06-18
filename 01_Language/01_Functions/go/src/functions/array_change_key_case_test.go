package functions

import (
	"fmt"
	"testing"
)

func TestArrayChangeKeyCase(t *testing.T) {
	m := make(map[string]interface{})
	m["FirSt"] = 1
	m["FirST"] = 2
	m["SecOnd"] = 4
	fmt.Println(ArrayChangeKeyCase(m, CASE_LOWER))
	fmt.Println(ArrayChangeKeyCase(m, CASE_UPPER))
}
