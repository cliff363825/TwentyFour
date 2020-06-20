package main

import (
	"chatroom/client/process"
	"fmt"
	"log"
	"os"
)

var (
	userId   int
	userPwd  string
	userName string
)

func init() {
	log.SetFlags(log.LstdFlags | log.Lshortfile)
}

func main() {
	// 接收用户的选择
	var key int

	for {
		fmt.Println("\t\t\t欢迎登录多人聊天系统")
		fmt.Println("\t\t 1 登录聊天室")
		fmt.Println("\t\t 2 注册用户")
		fmt.Println("\t\t 3 退出系统")
		fmt.Println("\t\t 请选择（1-3）")

		fmt.Scanf("%d", &key)
		switch key {
		case 1:
			fmt.Println("登录聊天室")
			fmt.Println("请输入用户的id")
			fmt.Scanf("%d", &userId)

			fmt.Println("请输入用户的密码")
			fmt.Scanf("%s", &userPwd)

			var userProcess process.UserProcess
			userProcess.Login(userId, userPwd)
		case 2:
			fmt.Println("注册用户")
			fmt.Println("请输入用户id:")
			fmt.Scanf("%d", &userId)

			fmt.Println("请输入用户的密码")
			fmt.Scanf("%s", &userPwd)

			fmt.Println("请输入用户名")
			fmt.Scanf("%s", &userName)

			var userProcess process.UserProcess
			userProcess.Register(userId, userPwd, userName)
		case 3:
			fmt.Println("退出系统")
			os.Exit(0)
		case 4:
			fmt.Println("你的输入有误，请重新输入")
		}
	}
}
