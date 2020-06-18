package functions

import (
	"fmt"
	"io"
	"log"
	"os"
	"testing"
)

func TestFseek(t *testing.T) {
	file, err := os.Open("test.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	b := make([]byte, 4096)
	n, _ := file.Read(b)
	fmt.Println(string(b[0:n]))
	file.Seek(0, io.SeekStart)
	n, _ = file.Read(b)
	fmt.Println(string(b[0:n]))
}
