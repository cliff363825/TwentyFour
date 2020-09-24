package functions

import (
	"crypto"
	"golang.org/x/crypto/hkdf"
	"hash"
)

func HashHkdf(algo crypto.Hash, ikm []byte, length int, info []byte, salt []byte) ([]byte, error) {
	h := algo.New()
	kdf := hkdf.New(func() hash.Hash {
		return h
	}, ikm, salt, info)
	if length == 0 {
		length = h.Size()
	}
	result := make([]byte, length)
	n, err := kdf.Read(result)
	if err != nil {
		return nil, err
	}
	return result[0:n], nil
}
