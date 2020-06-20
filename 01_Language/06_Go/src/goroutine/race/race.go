package main

// go build -race race.go
// Found 2 data race(s)

import (
	"fmt"
	"time"
)

var (
	myMap map[int]int = make(map[int]int)
)

// factorial 函数就是计算 n!，将这个结果放入到 myMap
func factorial(n int) {
	res := 1
	for i := 1; i <= n; i++ {
		res *= n
	}

	// 这里我们将 res 放入到 myMap
	myMap[n] = res
}

func main() {
	// 我们这里开启多个协程完成这个任务
	for i := 1; i <= 200; i++ {
		go factorial(i)
	}

	// 休眠10秒钟
	time.Sleep(time.Second * 10)

	// 这里我们输出结果，遍历这个结果
	for k, v := range myMap {
		fmt.Printf("myMap[%v]=%v\n", k, v)
	}
}
