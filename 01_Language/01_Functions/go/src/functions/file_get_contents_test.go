package functions

import (
	"fmt"
	"testing"
)

func TestFileGetContents(t *testing.T) {
	fmt.Println(FileGetContents("test.txt"))
	fmt.Println(FileGetContents("https://www.onevgo.com"))
}
