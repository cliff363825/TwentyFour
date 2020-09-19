package functions

import "crypto/subtle"

func HashEquals(knownString, userString []byte) bool {
	return subtle.ConstantTimeCompare(knownString, userString) == 1
}
