package hello

import (
	"fmt"
	"testing"
)

func checkFunc(b byte) byte {
	return b
}

func TestSwitch(t *testing.T) {
	var key byte
	//var key1 int = int('3')
	//fmt.Println("请输入一个字符：")
	//fmt.Scanf("%c", &key)

	switch checkFunc(key) {
	case 'a', 'A': // case 后面可以有多个表达式
		fmt.Println("周一，猴子穿新衣")
	case 'b', 'B':
		fmt.Println("周二，猴子当小二")
	case 'c', 'C':
		fmt.Println("周三，猴子爬雪山")
	//case 'A': // 错误，因为前面我们有常量 'A'，因此重复，就会报错
	//	fmt.Println("周四，呵呵")
	//case key1: // 错误，原因是 key1 的数据类型和 key 不一致
	//	fmt.Println("周四，呵呵")
	default:
		fmt.Println("输入有误")
	}

	// switch 后也可以不带表达式，类似 if - else 分支来使用
	var score int = 30
	switch {
	case score >= 90:
		fmt.Println("成绩优秀")
	case score >= 70:
		fmt.Println("成绩优良")
	case score >= 60:
		fmt.Println("及格")
	default:
		fmt.Println("不及格")
	}

	// switch 后也可以直接声明/定义一个变量，分号结束，不推荐
	switch score1 := 90; {
	case score1 >= 90:
		fmt.Println("成绩优秀")
	case score1 >= 70:
		fmt.Println("成绩优良")
	case score1 >= 60:
		fmt.Println("及格")
	default:
		fmt.Println("不及格")
	}

	// switch 的穿透 fallthrough
	var num int = 10
	switch num {
	case 10:
		fmt.Println("ok1")
		fallthrough // 默认只能穿透一层
	case 20:
		fmt.Println("ok2")
		fallthrough
	case 30:
		fmt.Println("ok3")
	default:
		fmt.Println("没有匹配到")
	}
}

func Calc(n1 float64, n2 float64, operator byte) float64 {
	var res float64
	switch operator {
	case '+':
		res = n1 + n2
	case '-':
		res = n1 - n2
	case '*':
		res = n1 * n2
	case '/':
		res = n1 / n2
	default:
		fmt.Println("操作符号错误")
	}
	return res
}

func TestSwitch2(t *testing.T) {
	n1 := 1.2
	n2 := 2.3
	res := Calc(n1, n2, '+')
	fmt.Println("result=", res)
}
