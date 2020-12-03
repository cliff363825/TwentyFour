package functions

import (
	"strings"
	"unicode"
)

func Ltrim(str string, charlist string) string {
	if charlist == "" {
		return strings.TrimLeftFunc(str, unicode.IsSpace)
	}
	return strings.TrimLeft(str, charlist)
}
