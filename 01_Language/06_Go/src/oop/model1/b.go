package model1

import (
	"fmt"
	"oop/model"
)

type B struct {
	model.A
	Name string
}

func (b *B) SayOk() {
	fmt.Println("B.SayOk...", b.Name)
}
