package model

import "fmt"

type Graduate struct {
	*Student
}

func (g *Graduate) Testing() {
	fmt.Printf("大学生[%v]正在考试中\n", g.Name)
}
