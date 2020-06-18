package functions

import (
	"bufio"
	"fmt"
	"os"
	"testing"
)

func TestFgets(t *testing.T) {
	file, err := os.Open("test.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()
	reader := bufio.NewReader(file)
	s, _ := reader.ReadString('\n')
	fmt.Print(s)
}

