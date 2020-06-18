package functions

import (
	"fmt"
	"testing"
)

func TestCtypeAlnum(t *testing.T) {
	strings := []string{"bCd1zyZ9", "foo!#$bar", ""}
	for _, v := range strings {
		if CtypeAlnum(v) {
			fmt.Println("The string " + v + " consists of all letters or digits.")
		} else {
			fmt.Println("The string " + v + " does not consist of all letters or digits.")
		}
	}
}
