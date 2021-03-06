package functions

import (
	"reflect"
)

func GetClassVars(cls interface{}) map[string]interface{} {
	m := make(map[string]interface{})

	rType := reflect.TypeOf(cls)
	rValue := reflect.ValueOf(cls)
	if rType.Kind() == reflect.Ptr {
		rType = rType.Elem()
		rValue = rValue.Elem()
	}

	for i := 0; i < rType.NumField(); i++ {
		if rValue.Field(i).CanInterface() {
			m[rType.Field(i).Name] = reflect.Zero(rValue.Field(i).Type()).Interface()
		}
	}

	return m
}
