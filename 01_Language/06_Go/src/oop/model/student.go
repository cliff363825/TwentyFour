package model

import "fmt"

type Student struct {
	Name  string
	Age   int
	score int
}

// 将 Pupil 和 Graduate 共有的方法也绑定到 *Student
func (stu *Student) ShowInfo() {
	fmt.Printf("学生名=%v 年龄=%v 成绩=%v\n", stu.Name, stu.Age, stu.score)
}

func (stu *Student) SetScore(score int) {
	stu.score = score
}

func (stu *Student) GetSum(num1 int, num2 int) int {
	return num1 + num2
}
