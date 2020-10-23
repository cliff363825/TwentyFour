package functions

import (
	"fmt"
	"testing"
)

func TestIsArray(t *testing.T) {
	yes := []string{"this", "is", "an array"}
	fmt.Println(IsArray(yes))

	no := "this is a string"
	fmt.Println(IsArray(no))

	var yes1 = &yes
	fmt.Println(IsArray(yes1))
	fmt.Println(IsArray(*yes1))
}

