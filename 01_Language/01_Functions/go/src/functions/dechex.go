package functions

import "strconv"

func Dechex(number int64) string {
	return strconv.FormatUint(uint64(number), 16)
}
