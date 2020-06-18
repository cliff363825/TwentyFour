package functions

import (
	"fmt"
	"testing"
)

func TestFilesize(t *testing.T) {
	fmt.Println(Filesize("test.txt"))
}

