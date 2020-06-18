package functions

import "strings"

func ExplodeLimit(delimiter string, s string, limit int) []string {
	res := strings.Split(s, delimiter)
	if limit < 0 {
		return res[0 : len(res)+limit]
	} else if limit == 0 {
		return []string{s}
	} else if limit < len(res) {
		return append(res[0:limit-1], strings.Join(res[limit-1:], delimiter))
	} else {
		return res
	}
}

func Explode(delimiter string, s string) []string {
	return strings.Split(s, delimiter)
}
