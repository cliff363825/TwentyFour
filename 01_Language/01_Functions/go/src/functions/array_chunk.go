package functions

import (
	"math"
)

func ArrayChunk(arr []interface{}, size int) [][]interface{} {
	capacity := math.Ceil(float64(len(arr)) / float64(size))
	result := make([][]interface{}, 0, int(capacity))
	chunk := make([]interface{}, 0, size)
	for _, v := range arr {
		if len(chunk) == size {
			result = append(result, chunk)
			chunk = make([]interface{}, 0, size)
		}
		chunk = append(chunk, v)
	}
	if len(chunk) > 0 {
		result = append(result, chunk)
	}
	return result
}
