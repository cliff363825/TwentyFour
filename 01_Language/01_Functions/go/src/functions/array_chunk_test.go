package functions

import (
	"fmt"
	"testing"
)

func TestArrayChunk(t *testing.T) {
	arr := []interface{}{"a", "b", "c", "d", "e"}
	fmt.Println(ArrayChunk(arr, 2))
}
