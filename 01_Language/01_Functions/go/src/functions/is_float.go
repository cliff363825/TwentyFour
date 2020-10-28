package functions

import "reflect"

func IsFloat(v interface{}) bool {
	rType := reflect.TypeOf(v)
	if rType.Kind() == reflect.Float32 {
		return true
	}
	return false
}
