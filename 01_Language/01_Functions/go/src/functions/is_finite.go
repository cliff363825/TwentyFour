package functions

import "math"

func IsFinite(v float64) bool {
	return !math.IsInf(v, 0) && !math.IsNaN(v)
}
