package hello

import (
	"fmt"
	"testing"
)

func TestLogic(t *testing.T) {
	var age int = 40

	// 逻辑运算符 &&
	if age > 30 && age < 50 {
		fmt.Println("ok1")
	}

	if age > 30 && age < 40 {
		fmt.Println("ok2")
	}

	// 逻辑运算符 ||
	if age > 30 || age < 50 {
		fmt.Println("ok3")
	}

	if age > 30 || age < 40 {
		fmt.Println("ok4")
	}

	// 逻辑运算符的使用 !
	if age > 30 {
		fmt.Println("ok5")
	}

	if !(age > 30) {
		fmt.Println("ok6")
	}

	// 短路与 &&
	// 说明 因为 age > 40 为 false，因此后面的 test() 不执行
	if age > 40 && test() {
		fmt.Println("ok...")
	}

	// 短路或 ||
	// 说明 因为 age >= 40 为 true，因此后面的test() 不执行
	if age >= 40 || test() {
		fmt.Println("ok...")
	}
}

func test() bool {
	fmt.Println("test run...")
	return true
}
