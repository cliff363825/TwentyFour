package functions

import (
	"fmt"
	"log"
	"os"
	"testing"
)

func TestFread(t *testing.T) {
	file, err := os.Open("test.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	b := make([]byte, 1024)
	n, err := file.Read(b)
	fmt.Println(string(b[:n]))
}

