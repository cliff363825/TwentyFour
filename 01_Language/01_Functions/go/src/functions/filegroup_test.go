package functions

import (
	"fmt"
	"testing"
)

func TestFilegroup(t *testing.T) {
	fmt.Println(Filegroup("test.txt"))
}

