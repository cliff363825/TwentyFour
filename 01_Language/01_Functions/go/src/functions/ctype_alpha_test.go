package functions

import (
	"fmt"
	"testing"
)

func TestCtypeAlpha(t *testing.T) {
	strings := []string{"KjgWZC", "arf12", ""}
	for _, v := range strings {
		if CtypeAlpha(v) {
			fmt.Println("The string " + v + " consists of all letters.")
		} else {
			fmt.Println("The string " + v + " does not consist of all letters.")
		}
	}
}
