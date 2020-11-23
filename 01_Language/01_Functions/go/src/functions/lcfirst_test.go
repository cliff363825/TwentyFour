package functions

import (
	"fmt"
	"testing"
)

func TestLcfirst(t *testing.T) {
	fmt.Println(Lcfirst("HelloWorld"))
	fmt.Println(Lcfirst("HELLO WORLD"))
	fmt.Println(Lcfirst(" Abc"))
	fmt.Println(Lcfirst(""))
}

