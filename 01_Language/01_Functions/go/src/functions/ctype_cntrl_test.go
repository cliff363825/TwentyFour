package functions

import (
	"fmt"
	"testing"
)

func TestCtypeCntrl(t *testing.T) {
	strings := map[string]string{"string1": "\n\r\t", "string2": "arf12", "": ""}
	for k, v := range strings {
		if CtypeCntrl(v) {
			fmt.Println("The string '" + k + "' consists of all control characters.")
		} else {
			fmt.Println("The string '" + k + "' does not consist of all control characters.")
		}
	}
}
