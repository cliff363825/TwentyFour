package functions

import (
	"fmt"
	"testing"
)

func TestEscapeshellarg(t *testing.T) {
	fmt.Println("ls " + Escapeshellarg("./"))
	fmt.Println("ls " + Escapeshellarg("','"))
}
