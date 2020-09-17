package functions

import (
	"crypto"
	"encoding/hex"
)

func Hash(algo crypto.Hash, data []byte) []byte {
	h := algo.New()
	h.Write(data)
	return h.Sum(nil)
}

func HashHexString(algo crypto.Hash, data []byte) string {
	return hex.EncodeToString(Hash(algo, data))
}
