package functions

import (
	"fmt"
	"testing"
)

func TestPregReplace(t *testing.T) {
	fmt.Println(PregReplace("(\\w+) (\\d+), (\\d+)", "${1}1,$3", "April 15, 2003"))
}
