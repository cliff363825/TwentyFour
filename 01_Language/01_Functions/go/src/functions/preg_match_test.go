package functions

import (
	"fmt"
	"testing"
)

func TestPregMatch(t *testing.T) {
	fmt.Println(PregMatch("php", "PHP is the web scripting language of choice."))
	fmt.Println(PregMatch("(?i)php", "PHP is the web scripting language of choice."))
}

func TestPregMatchAll(t *testing.T) {
	var matches [][]string
	fmt.Println(PregMatchAll("\\(?(?:(\\d{3})-)?\\)?\\d{3}-\\d{4}", "Call 555-1212 or 1-800-555-1212", &matches))
	fmt.Println(matches)
}
