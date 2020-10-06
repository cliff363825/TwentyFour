package functions

import "encoding/hex"

func Hex2bin(data string) ([]byte, error) {
	return hex.DecodeString(data)
}
