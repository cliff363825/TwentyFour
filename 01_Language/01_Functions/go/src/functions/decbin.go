package functions

import (
	"strconv"
)

func Decbin(number int64) string {
	return strconv.FormatUint(uint64(number), 2)
}
