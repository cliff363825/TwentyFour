package functions

import (
	"fmt"
	"testing"
)

func TestPregSplit(t *testing.T) {
	keywords := PregSplit("[\\s,]+", "hypertext language, programming", -1)
	fmt.Println(keywords)
}
