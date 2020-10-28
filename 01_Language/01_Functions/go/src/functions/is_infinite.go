package functions

import "math"

func IsInfinite(v float64) bool {
	return math.IsInf(v, 0)
}
