package main

import (
	"fmt"
	"unsafe"
)

func main() {
	var flag = true
	fmt.Printf("flag=(%T)%v,Sizeof(bool)=%v\n", flag, flag, unsafe.Sizeof(flag))

	if flag {
		fmt.Println("hello")
	} else {
		fmt.Println("hey")
	}

	//var a = bool(1) //error
	//var a = bool("true") // error
}
