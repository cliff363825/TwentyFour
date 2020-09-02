package functions

import (
	"fmt"
	"testing"
)

func TestGetimagesize(t *testing.T) {
	i, i2 := Getimagesize("test.gif")
	fmt.Println(i, i2)
}

