package functions

import "math"

func IsNan(val float64) bool {
	return math.IsNaN(val)
}
