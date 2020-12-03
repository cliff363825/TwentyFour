package functions

import (
	"fmt"
	"testing"
)

func TestLtrim(t *testing.T) {
	text := "\t\tThese are a few words :) ...  "
	binary := "\x09Example string\x0A"
	hello := "Hello World"
	fmt.Println(text)
	fmt.Println(binary)
	fmt.Println(hello)

	trimmed := Ltrim(text, "")
	fmt.Println(trimmed)

	trimmed = Ltrim(text, " \t.")
	fmt.Println(trimmed)

	trimmed = Ltrim(hello, "Hdle")
	fmt.Println(trimmed)
}
