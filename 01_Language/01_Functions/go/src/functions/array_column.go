package functions

func ArrayColumn(arr []map[string]interface{}, column string) []interface{} {
	res := make([]interface{}, 0, len(arr))
	for _, m := range arr {
		if v, ok := m[column]; ok {
			res = append(res, v)
		}
	}
	return res
}

func ArrayColumnIndex(arr []map[string]interface{}, column string, indexKey string) map[interface{}]interface{} {
	res := make(map[interface{}]interface{}, len(arr))
	for _, m := range arr {
		if v, ok := m[column]; ok {
			if k, ok := m[indexKey]; ok {
				res[k] = v
			}
		}
	}
	return res
}
