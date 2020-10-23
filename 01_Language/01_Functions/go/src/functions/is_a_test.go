package functions

import (
	"fmt"
	"reflect"
	"testing"
)

type WidgetFactory struct {
	Oink string
}

func TestIsA(t *testing.T) {
	WF := WidgetFactory{}
	WF.Oink = "moo"
	var WFInterface interface{} = WF
	WF2, ok := WFInterface.(WidgetFactory)
	fmt.Println(WF2, ok) // {moo} true

	fmt.Println(WF, WF2) // {moo} {moo}
	WF2.Oink = "moo2"
	fmt.Println(WF, WF2) // {moo} {moo2}
}

func TestIsA2(t *testing.T) {
	WF := WidgetFactory{}
	WF.Oink = "moo"
	var WFInterface interface{} = &WF
	WF2, ok := WFInterface.(*WidgetFactory)
	fmt.Println(WF2, ok) // {moo} true

	fmt.Println(WF, WF2) // {moo} {moo}
	WF2.Oink = "moo2"
	fmt.Println(WF, WF2) // {moo} {moo2}

	fmt.Printf("%p, %p\n", &WF, WF2)

	/*
	| address | var_name | data |
	   0x0001    WF        {Oink: "moo"}
	   0x0002    WFInterface  0x0001
	   0x0003    WF2          0x0001
	=> WF2 == &WF == 0x0001
	 */
}

func TestIsA3(t *testing.T) {
	WF := WidgetFactory{}
	WF.Oink = "moo"
	var WFInterface interface{} = WF
	rType := reflect.TypeOf(WFInterface)
	fmt.Println(rType.Kind())
	fmt.Printf("%T(%v)\n", WFInterface, WFInterface)
}

