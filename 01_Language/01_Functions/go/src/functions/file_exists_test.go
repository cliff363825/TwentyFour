package functions

import (
	"fmt"
	"testing"
)

func TestFileExists(t *testing.T) {
	fmt.Println(FileExists("test.txt"))
}

