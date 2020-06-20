package oop

import (
	"fmt"
	"oop/model"
	"testing"
)

func TestOop(t *testing.T) {
	var p = model.NewPerson("smith")
	_ = p.SetAge(-1)
	p.SetSalary(10000)
	fmt.Printf("p=(%T)%v\n", p, p)
}
