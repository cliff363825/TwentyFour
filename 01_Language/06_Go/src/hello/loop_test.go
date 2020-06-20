package hello

import (
	"fmt"
	"math/rand"
	"strconv"
	"testing"
	"time"
)

func TestLoop(t *testing.T) {
	for i := 1; i < 10; i++ {
		//fmt.Println("你好" + strconv.Itoa(i))
		fmt.Println("你好" + strconv.FormatInt(int64(i), 10))
	}
}

func TestLoop1(t *testing.T) {
	// 字符串遍历方式1-传统方式
	var str string = "hello,world!"
	for i := 0; i < len(str); i++ {
		fmt.Println(string(str[i]))
	}
}

func TestLoop2(t *testing.T) {
	// 字符串遍历方式2-for-range
	var str string = "abc~ok"
	for k, v := range str {
		fmt.Printf("k=%v,v=%v\n", k, string(v))
	}
}

func TestLoop3(t *testing.T) {
	var str string = "hello,中国!"
	str2 := []rune(str)
	for i := 0; i < len(str2); i++ {
		fmt.Println(string(str2[i]))
	}
}

func TestLoop4(t *testing.T) {
	// 我们为了生成一个随机数，还需要给 rand 设置种子
	// time.Now().Unix() 返回一个从 1970-01-01 00:00:00 到现在的秒数
	//rand.Seed(time.Now().Unix())
	rand.Seed(time.Now().UnixNano())
	// 随机生成 1 - 100 的整数
	i := rand.Intn(100) + 1
	fmt.Println(i)
}

func TestLoop5(t *testing.T) {
label1: // 设置一个标签
	for i := 'a'; i < 'f'; i++ {
		//label2:
		for j := 0; j < 6; j++ {
			if j == 2 {
				//break // break 默认会跳出最近的for循环
				//break label2
				break label1
			}
			fmt.Println(string(i) + "," + strconv.Itoa(j))
		}
	}
}

func TestLoop6(t *testing.T) {
label1:
	for i := 'a'; i < 'f'; i++ {
		//label2:
		for j := 0; j < 6; j++ {
			if j == 2 {
				//continue
				//continue label2
				continue label1
			}
			fmt.Println(string(i) + "," + strconv.Itoa(j))
		}
	}
}
