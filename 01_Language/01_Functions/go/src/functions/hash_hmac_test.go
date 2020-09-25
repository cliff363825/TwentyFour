package functions

import (
	"crypto"
	"fmt"
	"testing"
)

func TestHashHmac(t *testing.T) {
	fmt.Println(HashHmac(crypto.RIPEMD160, []byte("The quick brown fox jumped over the lazy dog."), []byte("secret")))
}

