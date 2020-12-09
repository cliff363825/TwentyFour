package functions

import (
	"fmt"
	"testing"
)

func TestMicrotime(t *testing.T) {
	fmt.Printf("%f\n", Microtime())
}

