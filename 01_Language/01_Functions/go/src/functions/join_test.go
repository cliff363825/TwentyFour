package functions

import (
	"fmt"
	"testing"
)

func TestJoin(t *testing.T) {
	arr := []string{"lastname", "email", "phone"}
	fmt.Println(Join(",", arr))
}

