package oop

import (
	"fmt"
	"oop/model"
	"oop/model1"
	"testing"
)

func TestExtends1(t *testing.T) {
	// 当我们对结构体嵌入了匿名结构体使用方法会发生变化
	var pupil = &model.Pupil{}
	// Pupil 嵌入 Student 结构体，Student 结构体的属性会默认初始化为对应类型的 零值。
	fmt.Printf("pupil=(%T)%v\n", pupil, pupil) // pupil=(*model.Pupil)&{{ 0 0}}

	pupil.Student.Name = "小明"
	pupil.Student.Age = 10
	pupil.Testing()
	pupil.Student.SetScore(60)
	pupil.Student.ShowInfo()
	sum1 := pupil.Student.GetSum(1, 2)
	fmt.Printf("sum1=(%T)%v\n", sum1, sum1)

	fmt.Println("----------")

	var graduate = &model.Graduate{}
	// Graduate 嵌入 *Student 结构体指针，*Student 结构体指针会默认初始化为指针的 零值，即 nil。
	fmt.Printf("graduate=(%T)%v\n", graduate, graduate) // graduate=(*model.Graduate)&{<nil>}
	var student = &model.Student{}
	graduate.Student = student
	graduate.Student.Name = "大明"
	graduate.Student.Age = 20
	graduate.Testing()
	graduate.Student.SetScore(70)
	graduate.Student.ShowInfo()
	sum2 := graduate.Student.GetSum(10, 20)
	fmt.Printf("sum2=(%T)%v\n", sum2, sum2)
}

func TestExtends2(t *testing.T) {
	var b model1.B
	b.A.Name = "tom"
	//b.A.age = 18 // b.A.age undefined (cannot refer to unexported field or method age)
	b.A.SayOk() // A.SayOk... tom
	b.A.Hello() // A.Hello... tom

	// 上面的写法可以简化
	b.Name = "smith"
	b.SayOk() // B.SayOk... smith
	b.Hello() // A.Hello... tom
}

func TestExtends3(t *testing.T) {
	var c model1.C
	// 如果 c 没有 Name 字段，而 A 和 B 有 Name，这是就必须通过指定匿名结构体名字来区分
	// 所以 c.Name 就会包编译错误，这个规则对方法也是一样的
	//c.Name = "tom" // ambiguous selector c.Name
	c.A.Name = "tom"
}

func TestExtends4(t *testing.T) {
	// 嵌套匿名结构体后，也可以在创建结构体变量（实例）时，直接指定各个匿名结构体字段的值
	tv := &model.TV{
		Goods: model.Goods{
			Name:  "电视机",
			Price: 5000,
		},
		Brand: model.Brand{
			Name:    "海尔",
			Address: "山东",
		},
	}
	fmt.Printf("tv=(%T)%v\n", tv, tv) // tv=(*model.TV)&{{电视机 5000} {海尔 山东}}

	tv2 := &model.TV2{
		Goods: &model.Goods{
			Name:  "电视机01",
			Price: 5001,
		},
		Brand: &model.Brand{
			Name:    "海尔01",
			Address: "山东01",
		},
	}
	fmt.Printf("tv2=(%T)%v\n", tv2, tv2) // tv2=(*model.TV2)&{0xc00005e0e0 0xc00005e100}
}

func TestExtends5(t *testing.T) {
	monkey := &model.LittleMonkey{
		Monkey: model.Monkey{
			Name: "悟空",
		},
	}
	monkey.Climbing()
	monkey.Flying()
}
