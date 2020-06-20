package datatype

import (
	"fmt"
	"testing"
	"unsafe"
)

func TestBool(t *testing.T) {
	var b bool
	// b=(bool)false, Sizeof(bool)=1
	fmt.Printf("b=(%T)%v, Sizeof(bool)=%v\n", b, b, unsafe.Sizeof(b))
}
