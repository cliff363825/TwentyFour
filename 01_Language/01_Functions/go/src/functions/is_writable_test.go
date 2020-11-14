package functions

import (
	"fmt"
	"testing"
)

func TestIsWritable(t *testing.T) {
	fmt.Println(IsWritable("test.txt"))
}

