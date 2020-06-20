package hello

import (
	"fmt"
	"testing"
)

func TestIf(t *testing.T) {
	var age int
	//fmt.Println("请输入年龄：")
	//fmt.Scanln(&age)

	if age >= 18 {
		fmt.Println("你年龄大于18，要对自己的行为负责")
	}

	if age2 := 16; age2 >= 18 {
		fmt.Println("你年龄大于18，要对自己的行为负责2")
	} else {
		fmt.Println("naive")
	}
}
