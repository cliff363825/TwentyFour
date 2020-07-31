package functions

import (
	"fmt"
	"testing"
)

type myclass struct {
}

func (cls *myclass) Myfunc1() bool {
	return true
}

func (cls *myclass) Myfunc2() bool {
	return true
}

func TestGetClassMethods(t *testing.T) {
	methods := GetClassMethods(&myclass{})
	fmt.Println(len(methods), methods)
}
