package main

import "fmt"

func main() {
	// 方式1 fmt.Scanln
	var name string
	var age int
	var salary float32
	var isPass bool

	fmt.Println("请输入姓名:")
	// 当程序执行到 fmt.Scanln(&name)，程序会停止在这里，等待用户输入，并回车
	fmt.Scanln(&name)

	fmt.Println("请输入年龄:")
	fmt.Scanln(&age)

	fmt.Println("请输入薪水：")
	fmt.Scanln(&salary)

	fmt.Println("请输入是否通过考试：")
	fmt.Scanln(&isPass)

	fmt.Printf("名字是 %v\n年龄是 %v\n薪水是 %v\n是否通过考试 %v\n", name, age, salary, isPass)

	// 方式2 fmt.Scanf,可以按指定的格式输入
	fmt.Println("请输入你的姓名，年龄，薪水，是否通过考试，使用空格隔开")
	fmt.Scanf("%s %d %f %t", &name, &age, &salary, &isPass)
	fmt.Printf("名字是 %v\n年龄是 %v\n薪水是 %v\n是否通过考试 %v\n", name, age, salary, isPass)
}
