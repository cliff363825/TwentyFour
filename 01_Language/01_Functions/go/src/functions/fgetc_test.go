package functions

import (
	"fmt"
	"os"
	"testing"
)

func TestFgetc(t *testing.T) {
	file, err := os.OpenFile("test.txt", os.O_RDONLY, 0644)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()
	b := make([]byte, 1)
	for {
		n, err := file.Read(b)
		if err != nil {
			break
		}
		fmt.Println(string(b[0:n]))
	}
}
