package functions

import "reflect"

func IsDouble(v interface{}) bool {
	rType := reflect.TypeOf(v)
	if rType.Kind() == reflect.Float64 {
		return true
	}
	return false
}
