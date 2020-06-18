package functions

import (
	"bufio"
	"fmt"
	"os"
	"testing"
)

func TestFclose(t *testing.T) {
	file, err := os.Open("test.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer func() {
		file.Close()
		fmt.Println("Close!")
	}()
	reader := bufio.NewReader(file)
	for {
		s, err := reader.ReadString('\n')
		fmt.Print(s)
		if err != nil {
			break
		}
	}
}
