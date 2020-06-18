package functions

import (
	"fmt"
	"testing"
)

func TestFileatime(t *testing.T) {
	fmt.Println(Fileatime("test.txt"))
}

