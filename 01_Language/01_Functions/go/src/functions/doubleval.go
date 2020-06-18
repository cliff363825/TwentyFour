package functions

import (
	"strconv"
	"strings"
)

func Doubleval(s string) (float64, error) {
	if len(s) == 0 {
		return 0, nil
	}
	var res string
	var (
		digit bool
		sign  bool
		dot   bool
	)
	for _, c := range s {
		if c >= '0' && c <= '9' {
			res += string(c)
			digit = true
		} else if c == '+' || c == '-' {
			if digit || sign || dot {
				break
			}
			res += string(c)
			sign = true
		} else if c == '.' {
			if dot {
				break
			}
			if !digit {
				res += "0"
				digit = true
			}
			res += string(c)
			dot = true
		} else if c == ' ' {
			if digit || sign || dot {
				break
			}
		} else {
			if digit || sign || dot {
				break
			}
			return 0, nil
		}
	}
	if !digit {
		return 0, nil
	}
	if strings.HasSuffix(res, ".") {
		res = res[:len(res)-1]
	}
	return strconv.ParseFloat(res, 64)
}
