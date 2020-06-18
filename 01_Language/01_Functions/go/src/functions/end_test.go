package functions

import (
	"fmt"
	"testing"
)

func TestEnd(t *testing.T) {
	fruits := []string{"apple", "banana", "cranberry"}
	fmt.Println(fruits[len(fruits)-1])
}
