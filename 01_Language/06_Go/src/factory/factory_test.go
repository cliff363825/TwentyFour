package factory

import (
	"factory/model"
	"fmt"
	"testing"
)

func TestFactory(t *testing.T) {
	// 创建一个Student实例
	//var stu = model.Student{
	//	Name: "tom",
	//	Score: 78.9,
	//}

	// 当student结构体是首字母小写，我们可以通过工厂模式来解决
	var stu = model.NewStudent("tom", 88.8)
	fmt.Printf("stu=(%T)%v\n", stu, stu) // stu=(*model.student)&{tom 88.8}
}
