package functions

import (
	"fmt"
	"testing"
)

func TestGethostbyaddr(t *testing.T) {
	s := Gethostbyaddr("193.112.23.137")
	//s := Gethostbyaddr("127.0.0.1")
	fmt.Println(s)
}

