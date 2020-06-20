package datatype

import (
	"fmt"
	"testing"
	"unsafe"
)

func TestFloat(t *testing.T) {
	var f32 float32
	var f64 float64

	// f32=(float32)0, Sizeof(float32)=4
	fmt.Printf("f32=(%T)%v, Sizeof(float32)=%v\n", f32, f32, unsafe.Sizeof(f32))

	// f64=(float64)0, Sizeof(float64)=8
	fmt.Printf("f64=(%T)%v, Sizeof(float64)=%v\n", f64, f64, unsafe.Sizeof(f64))
}
