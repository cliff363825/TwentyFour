package functions

import (
	"fmt"
	"testing"
)

func TestInetPton(t *testing.T) {
	fmt.Println(InetPton("127.0.0.1"))
	fmt.Println(InetPton("::1"))
}

