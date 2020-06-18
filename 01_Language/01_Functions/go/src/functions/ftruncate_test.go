package functions

import (
	"fmt"
	"io"
	"log"
	"os"
	"testing"
)

func TestFtruncate(t *testing.T) {
	file, err := os.OpenFile("test.txt", os.O_RDWR, 0644)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	file.Truncate(10)
	file.Seek(0, io.SeekStart)
	info, _ := file.Stat()
	b := make([]byte, info.Size())
	file.Read(b)
	fmt.Println(string(b))
}
