package functions

import (
	"crypto"
	"fmt"
	"testing"
)

func TestHashHkdf(t *testing.T) {
	intputKey := []byte("abcdef")
	salt := []byte("abcdef")

	encryptionKey, err := HashHkdf(crypto.SHA256, intputKey, 32, []byte("aes-256-encryption"), salt)
	fmt.Println(string(encryptionKey), err)
	authenticationKey, err := HashHkdf(crypto.SHA256, intputKey, 32, []byte("sha-256-authentication"), salt)
	fmt.Println(string(authenticationKey), err)
}

