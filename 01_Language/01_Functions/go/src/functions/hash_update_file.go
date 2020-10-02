package functions

import (
	"bufio"
	"hash"
	"os"
)

func HashUpdateFile(context hash.Hash, filename string) bool {
	file, err := os.Open(filename)
	if err != nil {
		return false
	}
	defer file.Close()

	reader := bufio.NewReader(file)
	b := make([]byte, 8192)
	for {
		n, err := reader.Read(b)
		if err != nil {
			break
		}

		context.Write(b[0:n])
	}
	return true
}
