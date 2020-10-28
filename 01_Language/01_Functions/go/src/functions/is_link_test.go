package functions

import (
	"fmt"
	"testing"
)

func TestIsLink(t *testing.T) {
	fmt.Println(IsLink("test"))
	fmt.Println(IsLink("test.txt"))
}
