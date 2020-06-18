package functions

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"testing"
)

func TestFpassthru(t *testing.T) {
	file, err := os.OpenFile("test.txt", os.O_RDONLY, 0666)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	reader := bufio.NewReader(file)
	b, _ := reader.ReadByte()
	fmt.Println(string(b))
	s, _ := reader.ReadString('\n')
	fmt.Println(s)
}
