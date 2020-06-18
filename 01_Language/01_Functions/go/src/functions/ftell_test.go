package functions

import (
	"fmt"
	"io"
	"log"
	"os"
	"testing"
)

func TestFtell(t *testing.T) {
	file, err := os.Open("test.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	b := make([]byte, 12)
	n, _ := file.Read(b)
	fmt.Println(string(b[0:n]))
	ret, _ := file.Seek(0, io.SeekCurrent)
	fmt.Println(ret)
}
