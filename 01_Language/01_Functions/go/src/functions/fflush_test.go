package functions

import (
	"bufio"
	"fmt"
	"os"
	"testing"
)

func TestFflush(t *testing.T) {
	file, err := os.OpenFile("test.txt", os.O_RDWR|os.O_CREATE, 0644)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()
	writer := bufio.NewWriter(file)
	i, _ := writer.WriteString("hello world")
	writer.Flush()
	file.Truncate(int64(i))
}
