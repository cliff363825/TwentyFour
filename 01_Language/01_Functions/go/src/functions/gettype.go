package functions

import (
	"reflect"
)

func Gettype(v interface{}) string {
	t := reflect.TypeOf(v)
	if t == nil {
		return "nil"
	}
	return t.Kind().String()
}
