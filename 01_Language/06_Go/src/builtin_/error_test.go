package builtin_

import (
	"errors"
	"fmt"
	"testing"
)

func TestError(t *testing.T) {
	// 使用 defer + recover() 来捕获和处理异常
	defer func() {
		/*
		err := recover() // recover() 内置函数，可以捕获到异常
		if err != nil { // 说明捕获到错误
			fmt.Printf("err=%v\n", err)
		}
		*/

		// 简洁写法
		// php: error_get_last()
		if err := recover(); err != nil {
			fmt.Println("err=", err)
		}
	}()

	num1 := 10
	num2 := 0
	i := num1 / num2 // panic: runtime error: integer divide by zero
	fmt.Printf("i=(%T)%v\n", i, i)
}

func ReadConf(name string) error {
	if name == "config.ini" {
		// 读取...
		return nil
	} else {
		// 自定义错误
		// new Exception()
		return errors.New("读取文件错误")
	}
}

func TestError2(t *testing.T) {
	err := ReadConf("config.ini")
	//err := ReadConf("config2.ini")
	if err != nil {
		// 如果读取文件发生错误，就输出这个错误，并终止程序
		// throw exception
		panic(err)
	}

	fmt.Println("run continue...")
}
