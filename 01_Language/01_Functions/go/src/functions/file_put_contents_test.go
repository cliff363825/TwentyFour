package functions

import (
	"fmt"
	"testing"
)

func TestFilePutContents(t *testing.T) {
	err := FilePutContents("test.txt", []byte("John Smith\n"), true, 0644)
	fmt.Println(err)
	fmt.Println(FileGetContents("test.txt"))
}
