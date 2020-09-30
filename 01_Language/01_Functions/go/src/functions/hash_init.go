package functions

import (
	"crypto"
	"hash"
)

func HashInit(algo crypto.Hash) hash.Hash {
	return algo.New()
}
