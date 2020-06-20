package process

import (
	"chatroom/client/utils"
	"chatroom/common/message"
	"encoding/json"
	"fmt"
	"log"
	"net"
	"os"
)

// 显示登录成功后的界面
func ShowMenu() {
	fmt.Println("\t\t 恭喜XXX登录成功")
	fmt.Println("\t\t 1. 显示在线用户列表")
	fmt.Println("\t\t 2. 发送消息")
	fmt.Println("\t\t 3. 信息列表")
	fmt.Println("\t\t 4. 退出系统")
	fmt.Println("请选择(1-4):")

	var key int
	var content string

	fmt.Scanf("%d", &key)
	switch key {
	case 1:
		//fmt.Println("显示在线用户列表")
		OutputOnlineUser()
	case 2:
		//fmt.Println("发送消息")
		fmt.Println("你想对大家说点什么")
		fmt.Scanf("%s", &content)

		process := &SmsProcess{}
		process.SendGroupMsg(content)
	case 3:
		fmt.Println("信息列表")
	case 4:
		fmt.Println("退出系统")
		os.Exit(0)
	default:
		fmt.Println("你输入的选项不正确")
	}
}

// 和服务器保持通讯
func ServerProcessMsg(conn net.Conn) {
	// 创建一个 Transfer，不停的读取服务器发送的消息
	transfer := utils.NewTransfer(conn, 8096)

	for {
		fmt.Println("客户端等待接收服务端发送的消息")
		msg, err := transfer.ReadPkg()
		if err != nil {
			log.Println("[client]read pkg err=", err)
			return
		}

		fmt.Println("客户端接收到消息", msg)

		// 如果读取到消息，又是下一步处理逻辑
		switch msg.Type {
		case message.NotifyUserStatusMsgType:
			// 有人上线了
			// 1. 取出 NotifyUserStatusMsg
			var notifyUserStatusMsg *message.NotifyUserStatusMsg
			err := json.Unmarshal([]byte(msg.Data), &notifyUserStatusMsg)
			if err != nil {
				log.Println("[client]unmarshal err=", err)
				return
			}
			// 2. 把这个用户的信息，状态保存到客户map中
			UpdateUserStatus(notifyUserStatusMsg)
			OutputOnlineUser()
		case message.SmsMsgType: // 有人群发信息
			OutputGroupMsg(msg)
		default:
			fmt.Println("服务端返回了未知的消息类型", msg)
		}
	}
}
