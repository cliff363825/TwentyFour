package functions

import (
	"fmt"
	"testing"
)

func TestAddslashes(t *testing.T) {
	fmt.Println(Addslashes("' \" \\ \x00"))
}

