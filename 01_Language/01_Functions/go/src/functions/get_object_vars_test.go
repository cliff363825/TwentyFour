package functions

import (
	"fmt"
	"testing"
)

type foo struct {
	a int
	B int
	C int
	d int
}

func (f *foo) test() {
	fmt.Println(GetObjectVars(f))
}

func TestGetObjectVars(t *testing.T) {
	f := &foo{B:1, C:2}
	f.test()
	fmt.Println("===========")
	fmt.Println(GetObjectVars(f))
}

