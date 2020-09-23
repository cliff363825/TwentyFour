package functions

import (
	"crypto"
	"fmt"
	"testing"
)

func TestHashFile(t *testing.T) {
	FilePutContents("test.txt", []byte("The quick brown fox jumped over the lazy dog."), false, 0644)
	fmt.Println(HashFile(crypto.MD5, "test.txt"))
}

