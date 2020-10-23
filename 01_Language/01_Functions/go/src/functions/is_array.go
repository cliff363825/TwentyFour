package functions

import "reflect"

func IsArray(v interface{}) bool {
	rType := reflect.TypeOf(v)
	if rType.Kind() == reflect.Array || rType.Kind() == reflect.Slice {
		return true
	}
	return false
}
