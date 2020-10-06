package functions

import (
	"fmt"
	"testing"
)

func TestHex2bin(t *testing.T) {
	bytes, _ := Hex2bin("6578616d706c65206865782064617461")
	fmt.Println(string(bytes))
}

