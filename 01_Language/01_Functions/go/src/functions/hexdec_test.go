package functions

import (
	"fmt"
	"testing"
)

func TestHexdec(t *testing.T) {
	fmt.Println(Hexdec("See", 64))
	fmt.Println(Hexdec("ee", 64))
	fmt.Println(Hexdec("that", 64))
	fmt.Println(Hexdec("a0", 64))
}

