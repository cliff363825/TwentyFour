package functions

import "errors"

func ArrayCombine(keys []interface{}, values []interface{}) (map[interface{}]interface{}, error) {
	if len(keys) != len(values) {
		return nil, errors.New("len(keys) != len(values)")
	}
	res := make(map[interface{}]interface{}, len(keys))
	for i, v := range keys {
		res[v] = values[i]
	}
	return res, nil
}
