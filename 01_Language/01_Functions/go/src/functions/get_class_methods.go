package functions

import "reflect"

func GetClassMethods(cls interface{}) []reflect.Method {
	res := make([]reflect.Method, 0, 16)
	cType := reflect.TypeOf(cls)
	for i := 0; i < cType.NumMethod(); i++ {
		res = append(res, cType.Method(i))
	}
	return res
}
