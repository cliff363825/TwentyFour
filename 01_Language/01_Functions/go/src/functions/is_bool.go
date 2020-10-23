package functions

import "reflect"

func IsBool(v interface{}) bool {
	rType := reflect.TypeOf(v)
	if rType.Kind() == reflect.Bool {
		return true
	}
	return false
}
