package functions

import (
	"crypto"
	"golang.org/x/crypto/pbkdf2"
)

func HashPbkdf2(algo crypto.Hash, password []byte, salt []byte, iterations int, length int) []byte {
	if length == 0 {
		length = algo.Size()
	}
	return pbkdf2.Key(password, salt, iterations, length, algo.New)
}
