package functions

import (
	"math/rand"
	"time"
)

func MtRand() int32 {
	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	return r.Int31()
}

func MtRandRange(min, max int32) int32 {
	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	return min + r.Int31n(max-min)
}
