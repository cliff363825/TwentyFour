package functions

import (
	"crypto"
	"fmt"
	_ "golang.org/x/crypto/ripemd160"
	"testing"
)

func TestHash(t *testing.T) {
	fmt.Println(Hash(crypto.RIPEMD160, []byte("The quick brown fox jumped over the lazy dog.")))
	fmt.Println(HashHexString(crypto.RIPEMD160, []byte("The quick brown fox jumped over the lazy dog.")))
}

