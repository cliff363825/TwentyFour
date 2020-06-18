package functions

import (
	"fmt"
	"testing"
)

func TestFilectime(t *testing.T) {
	fmt.Println(Filectime("test.txt"))
}

