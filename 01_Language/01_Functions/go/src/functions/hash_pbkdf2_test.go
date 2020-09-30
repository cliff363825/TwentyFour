package functions

import (
	"crypto"
	"encoding/hex"
	"fmt"
	"testing"
)

func TestHashPbkdf2(t *testing.T) {
	password := []byte("password")
	iterations := 1000
	salt := []byte("abcdef")
	fmt.Println(hex.EncodeToString(HashPbkdf2(crypto.SHA256, password, salt, iterations, 0)))
	fmt.Println(hex.EncodeToString(HashPbkdf2(crypto.SHA256, password, salt, iterations, 10)))
}

