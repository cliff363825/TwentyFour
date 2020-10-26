package functions

import "reflect"

func IsCallable(v interface{}) bool {
	rType := reflect.TypeOf(v)
	if rType.Kind() == reflect.Func {
		return true
	}
	return false
}
