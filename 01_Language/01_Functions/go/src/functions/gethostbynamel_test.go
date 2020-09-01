package functions

import (
	"fmt"
	"testing"
)

func TestGethostbynamel(t *testing.T) {
	fmt.Println(Gethostbynamel("www.onevgo.com"))
	fmt.Println(Gethostbynamel("ss.onevgo.com"))
}
