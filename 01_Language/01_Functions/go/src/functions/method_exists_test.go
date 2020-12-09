package functions

import (
	"crypto"
	"fmt"
	"testing"
)

func TestMethodExists(t *testing.T) {
	fmt.Println(MethodExists(crypto.MD5.New(), "Sum"))
}
