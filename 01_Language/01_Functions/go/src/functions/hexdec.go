package functions

import (
	"regexp"
	"strconv"
)

func Hexdec(hexString string, bitSize int) (int64, error) {
	pattern, _ := regexp.Compile("[^0-9A-Fa-f]")
	hexString = pattern.ReplaceAllString(hexString, "")
	result, err := strconv.ParseInt(hexString, 16, bitSize)
	return result, err
}
