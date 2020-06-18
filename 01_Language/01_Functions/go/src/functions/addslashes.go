package functions

import "strings"

func Addslashes(s string) string {
	return strings.ReplaceAll(strings.ReplaceAll(strings.ReplaceAll(strings.ReplaceAll(s,
		"\\", "\\\\"), "'", "\\'"), "\"", "\\\""), "\x00", "\\0")
}
