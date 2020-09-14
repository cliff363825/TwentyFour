package functions

import (
	"fmt"
	"testing"
)

func TestGlob(t *testing.T) {
	result, _ := Glob("*.txt")
	fmt.Println(result)
}
