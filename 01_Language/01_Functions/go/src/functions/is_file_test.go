package functions

import (
	"fmt"
	"testing"
)

func TestIsFile(t *testing.T) {
	fmt.Println(IsFile("test.txt"))
	fmt.Println(IsFile("/usr/bin/"))
}

