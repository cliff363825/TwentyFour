package functions

import (
	"fmt"
	"testing"
)

func TestIsInt(t *testing.T) {
	fmt.Println(IsInt(23))
	fmt.Println(IsInt("23"))
	fmt.Println(IsInt(23.5))
	fmt.Println(IsInt("23.5"))
	fmt.Println(IsInt(nil))
	fmt.Println(IsInt(true))
	fmt.Println(IsInt(false))
}

