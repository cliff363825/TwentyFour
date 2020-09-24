package functions

import (
	"fmt"
	"testing"
)

func TestAddslashes(t *testing.T) {
	s := "' \" \\ \x00"
	fmt.Println(s)
	fmt.Println(Addslashes(s))
}

