package functions

import (
	"crypto"
	"fmt"
	"testing"
)

func TestHashFinal(t *testing.T) {
	h := crypto.SHA1.New()
	h.Write([]byte("The quick brown fox jumped over the lazy dog."))
	fmt.Println(HashFinal(h))
}

