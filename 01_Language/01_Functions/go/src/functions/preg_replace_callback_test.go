package functions

import (
	"fmt"
	"testing"
)

func TestPregReplaceCallback(t *testing.T) {
	text := "April fools day is 04/01/2002\nLast christmas was 12/24/2001\n"
	fmt.Println(PregReplaceCallback("(\\d{2}/\\d{2}/)(\\d{4})", func(s string) string {
		fmt.Println(s)
		return s
	}, text))
}

