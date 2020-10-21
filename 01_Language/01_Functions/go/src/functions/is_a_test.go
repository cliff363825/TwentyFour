package functions

import (
	"fmt"
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
	fmt.Println(WF2, ok)

	fmt.Println(WF, WF2)
	WF2.Oink = "moo2"
	fmt.Println(WF, WF2)
	fmt.Printf("%p, %p\n", &WF, &WF2)
}
