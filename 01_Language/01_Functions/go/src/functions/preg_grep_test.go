package functions

import (
	"fmt"
	"testing"
)

func TestPregGrep(t *testing.T) {
	var arr = []string{"1.23", ".45", "abc"}
	arr1, _ := PregGrep("^(\\d+)?\\.\\d+$", arr)
	fmt.Println(arr1)

	arr2, _ := PregGrep("\\d+", arr)
	fmt.Println(arr2)
}
