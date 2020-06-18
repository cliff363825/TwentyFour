package functions

import (
	"fmt"
	"testing"
)

func TestFileowner(t *testing.T) {
	fmt.Println(Fileowner("test.txt"))
}
