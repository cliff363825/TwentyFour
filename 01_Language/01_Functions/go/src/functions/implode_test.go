package functions

import (
	"fmt"
	"testing"
)

func TestImplode(t *testing.T) {
	array := []string{"lastname", "email", "phone"}
	fmt.Println(Implode(",", array))
}

