package functions

import (
	"fmt"
	"testing"
)

func TestFilemtime(t *testing.T) {
	fmt.Println(Filemtime("test.txt"))
}

