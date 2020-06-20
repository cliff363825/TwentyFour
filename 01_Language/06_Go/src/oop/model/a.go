package model

import "fmt"

type A struct {
	Name string
	age  int
}

func (a *A) Hello() {
	fmt.Println("A.Hello...", a.Name)
}

func (a *A) SayOk() {
	fmt.Println("A.SayOk...", a.Name)
}
