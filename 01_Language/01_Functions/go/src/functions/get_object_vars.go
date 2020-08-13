package functions

import "reflect"

func GetObjectVars(obj interface{}) map[string]interface{} {
	m := make(map[string]interface{})

	rType := reflect.TypeOf(obj)
	rValue := reflect.ValueOf(obj)
	if rType.Kind() == reflect.Ptr {
		rType = rType.Elem()
		rValue = rValue.Elem()
	}

	for i := 0; i < rType.NumField(); i++ {
		if rValue.Field(i).CanInterface() {
			m[rType.Field(i).Name] = rValue.Field(i).Interface()
		}
	}

	return m
}
