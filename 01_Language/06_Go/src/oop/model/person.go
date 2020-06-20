package model

import (
	"errors"
	"fmt"
)

type person struct {
	Name   string
	age    int
	salary float64
}

// 写一个工厂模式的函数，相当于构造函数
// option + enter -> Generate constructor ... 快速生成
func NewPerson(name string) *person {
	return &person{Name: name}
}

// 为了访问 age 和 salary 我们编写一对 SetXxx的方法和 GetXxx 的方法
// option + enter -> Generate getter and setter ... 快速生成
func (p *person) GetAge() int {
	return p.age
}

func (p *person) SetAge(age int) error {
	if age > 0 && age < 150 {
		p.age = age
		return nil
	} else {
		return errors.New("年龄范围不正确")
	}
}

func (p *person) GetSalary() float64 {
	return p.salary
}

func (p *person) SetSalary(salary float64) {
	if salary >= 3000 && salary <= 30000 {
		p.salary = salary
	} else {
		fmt.Println("薪水范围不对")
	}
}
