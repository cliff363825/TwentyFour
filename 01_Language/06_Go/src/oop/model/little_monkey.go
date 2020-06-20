package model

import "fmt"

type LittleMonkey struct {
	Monkey
}

// 让 LittleMon
func (m *LittleMonkey) Flying() {
	fmt.Println(m.Name, "通过学习，会飞翔。。。")
}
