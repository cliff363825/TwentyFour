package functions

import (
	"fmt"
	"testing"
)

func TestLevenshtein(t *testing.T) {
	str1 := "carrrot1"
	str2 := "carrots"
	fmt.Println(Levenshtein(str1, str2, 1, 1, 1))
	fmt.Println(Levenshtein(str2, str1, 1, 1, 1))
	fmt.Println(Levenshtein(str1, str2, 2, 3, 4))
	fmt.Println(Levenshtein(str2, str1, 2, 3, 4))
}

