package functions

import (
	"fmt"
	"testing"
)

func TestFileperms(t *testing.T) {
	fmt.Println(Fileperms("test.txt"))
}

