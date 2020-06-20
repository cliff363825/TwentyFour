package model

import "fmt"

type Pupil struct {
	Student
}

func (p *Pupil) Testing() {
	fmt.Printf("小学生[%v]正在考试中\n", p.Name)
}
