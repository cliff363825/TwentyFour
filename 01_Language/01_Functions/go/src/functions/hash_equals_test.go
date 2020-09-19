package functions

import (
	"fmt"
	"golang.org/x/crypto/bcrypt"
	"testing"
)

func TestHashEquals(t *testing.T) {
	expected, _ := bcrypt.GenerateFromPassword([]byte("12345"), 7)
	fmt.Println(string(expected))

	result := bcrypt.CompareHashAndPassword(expected, []byte("12345"))
	fmt.Println(result)
}
