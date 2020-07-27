package functions

import (
	"fmt"
	"reflect"
	"testing"
)

type bar struct {
}

func TestGetClass(t *testing.T) {
	b := bar{}
	rType := reflect.TypeOf(b)
	fmt.Println(rType.Name())
}
