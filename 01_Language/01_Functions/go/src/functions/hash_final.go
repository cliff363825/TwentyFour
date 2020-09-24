package functions

import (
	"encoding/hex"
	"hash"
)

func HashFinal(context hash.Hash) string {
	return hex.EncodeToString(context.Sum(nil))
}
