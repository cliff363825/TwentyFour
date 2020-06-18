package functions

import (
	"fmt"
	"testing"
)

func TestExec(t *testing.T) {
	fmt.Println(Exec("whoami"))
	fmt.Println(Exec("howareyou"))
}
