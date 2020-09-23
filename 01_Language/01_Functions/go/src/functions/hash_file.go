package functions

import (
	"bufio"
	"crypto"
	"encoding/hex"
	"os"
)

func HashFile(algo crypto.Hash, filename string) (string, error) {
	file, err := os.Open(filename)
	if err != nil {
		return "", err
	}
	defer file.Close()

	h := algo.New()
	reader := bufio.NewReader(file)
	b := make([]byte, 8192)
	for {
		n, err := reader.Read(b)
		if err != nil {
			break
		}

		h.Write(b[0:n])
	}
	return hex.EncodeToString(h.Sum(nil)), nil
}
