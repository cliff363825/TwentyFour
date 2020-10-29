package functions

import (
	"fmt"
	"testing"
)

func TestIsReadable(t *testing.T) {
	fmt.Println(IsReadable("test.txt"))
	fmt.Println(IsExecutable("test.txt"))
}

