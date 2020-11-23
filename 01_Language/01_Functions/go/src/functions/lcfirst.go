package functions

import "strings"

func Lcfirst(str string) string {
	if len(str) == 0 {
		return ""
	}
	return strings.ToLower(string(str[0])) + str[1:]
}
