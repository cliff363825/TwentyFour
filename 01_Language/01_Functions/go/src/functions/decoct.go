package functions

import "strconv"

func Decoct(number int64) string {
	return strconv.FormatUint(uint64(number), 8)
}
