package functions

import (
	"fmt"
	"testing"
)

func TestChr(t *testing.T) {
	str := "The string ends in escape: "
	str += string(Chr(27))
	fmt.Printf("%q\n", str)

	var bytes []byte = []byte{Chr(240), Chr(159), Chr(144), Chr(152)}
	fmt.Printf("%s\n", bytes)
}
