package functions

func ArrayChunk(arr []interface{}, size int) [][]interface{} {
	res := make([][]interface{}, 0)
	current := make([]interface{}, 0, size)
	for _, v := range arr {
		if len(current) == size {
			res = append(res, current)
			current = make([]interface{}, 0, size)
		}
		current = append(current, v)
	}
	if len(current) > 0 {
		res = append(res, current)
	}
	return res
}
