package functions

import "math"

func Log(arg float64, base float64) float64 {
	return math.Log(arg) / math.Log(base)
}
