package functions

import (
	"reflect"
)

func GetClassVars(cls interface{}) map[string]interface{} {
	rType := reflect.TypeOf(cls)
	rVal := reflect.ValueOf(cls)

	if rType.Kind() == reflect.Ptr {
		rType = rType.Elem()
		rVal = rVal.Elem()
	}

	res := make(map[string]interface{})
	for i := 0; i < rType.NumField(); i++ {
		putMap(res, rType.Field(i).Name, rVal.Field(i))
	}
	return res
}

func putMap(m map[string]interface{}, key string, value reflect.Value)  {
	defer func() {
		recover()
	}()
	m[key] = value.Interface()
}
