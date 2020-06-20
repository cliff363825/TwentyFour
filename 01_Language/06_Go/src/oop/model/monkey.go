package model

import "fmt"

type Monkey struct {
	Name string
}

func (m *Monkey) Climbing() {
	fmt.Println(m.Name, "生来会爬树。。。")
}
