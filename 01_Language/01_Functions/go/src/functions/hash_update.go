package functions

import "hash"

func HashUpdate(context hash.Hash, data []byte) bool {
	_, err := context.Write(data)
	if err != nil {
		return false
	}
	return true
}
