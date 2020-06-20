package process

import (
	"chatroom/client/model"
	"chatroom/common/message"
	"fmt"
)

// 客户端要维护的map
var OnlineUsers = make(map[int]*message.User, 10)
var CurUser model.CurUser

// 编写一个方法，处理返回的NotifyUserStatusMsg
func UpdateUserStatus(msg *message.NotifyUserStatusMsg) {
	user, ok := OnlineUsers[msg.UserId]
	if !ok {
		user = &message.User{
			UserId: msg.UserId,
		}
	}
	user.UserStatus = msg.Status

	OnlineUsers[msg.UserId] = user
}

func OutputOnlineUser() {
	fmt.Println("当前在线用户列表")
	for id, user := range OnlineUsers {
		fmt.Printf("用户[%v](ID:%v)\n", user.UserName, id)
	}
}
