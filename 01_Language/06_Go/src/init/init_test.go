package init

import (
	"fmt"
	_ "init/init_"
	"testing"
)

// 为了看到全局变量是先被初始化的，我们这里先写函数
var age = getAge()

func getAge() int {
	fmt.Println("getAge()...") // 1
	return 30
}

// init 函数，通常可以在 init 函数中完成初始化工作
func init() {
	fmt.Println("init()...") // 2
}

func myFunc() {
	fmt.Println("myFunc()...") // 3
}

func TestInit(t *testing.T) {
	//a init()....
	//b init()...
	//c init()....
	//getAge()...
	//init()...
	//=== RUN   TestInit
	//myFunc()...
	//--- PASS: TestInit (0.00s)
	//PASS
	myFunc()
}
