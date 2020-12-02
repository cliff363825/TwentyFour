package functions

import "strconv"

func Long2ip(properAddress int64) string {
	return strconv.Itoa(int(properAddress>>24&0xFF)) +
		"." + strconv.Itoa(int(properAddress>>16&0xFF)) +
		"." + strconv.Itoa(int(properAddress>>8&0xFF)) +
		"." + strconv.Itoa(int(properAddress&0xFF))
}
