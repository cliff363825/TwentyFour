package functions

import (
	"fmt"
	"testing"
)

func TestIsDir(t *testing.T) {
	fmt.Println(IsDir("test.txt"))
	fmt.Println(IsDir("test1/test2"))
	fmt.Println(IsDir(".."))
}

