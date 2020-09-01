package functions

import (
	"fmt"
	"testing"
)

func TestGethostbyname(t *testing.T) {
	fmt.Println(Gethostbyname("www.onevgo.com"))
	fmt.Println(Gethostbyname("ss.onevgo.com"))
}

