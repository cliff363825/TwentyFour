package functions

import (
	"fmt"
	"testing"
)

func TestDecbin(t *testing.T) {
	fmt.Println(Decbin(12))
	fmt.Println(Decbin(-12))
	fmt.Println(Decbin(26))
}

func TestDechex(t *testing.T) {
	fmt.Println(Dechex(10))
	fmt.Println(Dechex(-10))
	fmt.Println(Dechex(47))
}

func TestDecoct(t *testing.T) {
	fmt.Println(Decoct(15))
	fmt.Println(Decoct(-15))
	fmt.Println(Decoct(264))
}
