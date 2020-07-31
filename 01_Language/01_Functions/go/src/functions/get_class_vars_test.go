package functions

import (
	"fmt"
	"testing"
)

type TestCase struct {
	A int
	b int
	c int
}

func TestGetClassVars(t *testing.T) {
	fmt.Println(GetClassVars(TestCase{A:1,b:2,c:3}))
	fmt.Println(GetClassVars(&TestCase{A:1,b:2,c:3}))
}
