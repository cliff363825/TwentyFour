package functions

import (
	"bufio"
	"crypto/md5"
	"encoding/hex"
	"os"
)

func Md5File(filename string) (string, error) {
	file, err := os.Open(filename)
	if err != nil {
		return "", err
	}
	defer file.Close()

	h := md5.New()
	reader := bufio.NewReader(file)
	b := make([]byte, 8192)
	for true {
		n, err := reader.Read(b)
		if err != nil {
			break
		}

		h.Write(b[0:n])
	}
	return hex.EncodeToString(h.Sum(nil)), nil
}
