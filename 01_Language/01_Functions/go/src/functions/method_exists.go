package functions

import "reflect"

func MethodExists(object interface{}, methodName string) bool {
	rType := reflect.TypeOf(object)
	_, ok := rType.MethodByName(methodName)
	return ok
}
