package functions

import (
	"bufio"
	"crypto"
	"crypto/hmac"
	"encoding/hex"
	"os"
)

func HashHmacFile(alog crypto.Hash, filename string, key []byte) (string, error) {
	file, err := os.Open(filename)
	if err != nil {
		return "", err
	}
	defer file.Close()

	h := hmac.New(alog.New, key)
	reader := bufio.NewReader(file)
	b := make([]byte, 4096)
	for {
		n, err := reader.Read(b)
		if err != nil {
			break
		}
		h.Write(b[0:n])
	}
	return hex.EncodeToString(h.Sum(nil)), nil
}
