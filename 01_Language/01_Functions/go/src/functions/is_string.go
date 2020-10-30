package functions

import "reflect"

func IsString(v interface{}) bool {
	rType := reflect.TypeOf(v)
	return rType.Kind() == reflect.String
}
