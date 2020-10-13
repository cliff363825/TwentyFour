package functions

import (
	"fmt"
	"testing"
)

func TestInetNtop(t *testing.T) {
	fmt.Println(InetNtop([]byte{127, 0, 0, 1}))
	fmt.Println(InetNtop([]byte{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}))
}
