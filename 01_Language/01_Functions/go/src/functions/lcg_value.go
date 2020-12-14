package functions

import (
	"math/rand"
	"time"
)

func LcgValue() float64 {
	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	return r.Float64()
}
