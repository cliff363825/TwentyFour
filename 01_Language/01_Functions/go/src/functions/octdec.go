package functions

import "strconv"

func Octdec(octString string) (int64, error) {
	return strconv.ParseInt(octString, 8, 64)
}
