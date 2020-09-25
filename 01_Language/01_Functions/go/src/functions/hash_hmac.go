package functions

import (
	"crypto"
	"crypto/hmac"
	"encoding/hex"
)

func HashHmac(algo crypto.Hash, data []byte, key []byte) string {
	h := hmac.New(algo.New, key)
	h.Write(data)
	return hex.EncodeToString(h.Sum(nil))
}
