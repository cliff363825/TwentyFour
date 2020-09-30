package functions

import (
	"crypto"
	"fmt"
	"testing"
)

func TestHashHmacFile(t *testing.T) {
	FilePutContents("test.txt", []byte("The quick brown fox jumped over the lazy dog."), false, 0644)
	s, _ := HashHmacFile(crypto.MD5, "test.txt", []byte("secret"))
	fmt.Println(s)
}

