package functions

import (
	"crypto"
	"golang.org/x/crypto/hkdf"
)

func HashHkdf(algo crypto.Hash, ikm []byte, length int, info []byte, salt []byte) ([]byte, error) {
	kdf := hkdf.New(algo.New, ikm, salt, info)
	if length == 0 {
		length = algo.Size()
	}
	result := make([]byte, length)
	n, err := kdf.Read(result)
	if err != nil {
		return nil, err
	}
	return result[0:n], nil
}
