package process

import (
	"chatroom/common/message"
	"chatroom/server/model"
	"chatroom/server/utils"
	"encoding/json"
	"fmt"
	"log"
	"net"
)

type UserProcess struct {
	Conn   net.Conn
	UserId int
}

// 编写一个函数 serverProcessLogin函数，专门处理登录请求
func (p *UserProcess) ServerProcessLogin(msg *message.Message) error {
	// 核心代码
	// 1. 先从 msg 中取出 msg.Data，并直接反序列化成LoginMsg
	var loginMsg *message.LoginMsg
	err := json.Unmarshal([]byte(msg.Data), &loginMsg)
	if err != nil {
		log.Println("[server]unmarshal err=", err)
		return err
	}

	// 先声明一个 resMsg
	var resMsg = &message.Message{}
	resMsg.Type = message.LoginResMsgType

	// 在声明一个 LoginResMsg
	var loginResMsg = &message.LoginResMsg{}

	// 我们需要到redis数据库去完成验证
	// 1. 使用model.MyUserDao 到redis去验证
	user, err := model.MyUserDao.Login(loginMsg.UserId, loginMsg.UserPwd)
	if err != nil {
		if err == model.ERROR_USER_NOTEXISTS {
			loginResMsg.Code = 500
			loginResMsg.Error = err.Error()
		} else if err == model.ERROR_USER_PWD {
			loginResMsg.Code = 403
			loginResMsg.Error = err.Error()
		} else {
			loginResMsg.Code = 505
			loginResMsg.Error = "服务器内部错误"
		}
	} else {
		loginResMsg.Code = 200

		// 这里，因为用户登录成功，我们就把该登录成功的用户放入到 userMgr 中
		// 将登录成功的用户的 userId 赋给 this
		p.UserId = user.UserId
		UserMgr.AddOnlineUser(p)
		p.NotifyOthersOnlineUsers(loginMsg.UserId)

		// 将当前在线用户的id，放入到 loginResMsg.UsersId
		// 遍历 UserMgr.OnlineUsers
		for id, _ := range UserMgr.OnlineUsers {
			loginResMsg.UserIds = append(loginResMsg.UserIds, id)
		}
	}

	// 3. 将 loginResMsg 序列化
	loginResMsgBytes, err := json.Marshal(loginResMsg)
	if err != nil {
		log.Println("[server]marshal err=", err)
		return err
	}

	// 4 将data赋值给 resMsg
	resMsg.Data = string(loginResMsgBytes)

	// 5. 对 resMsg 进行序列化，准备发送
	resMsgBytes, err := json.Marshal(resMsg)
	if err != nil {
		fmt.Println("marshal err=", err)
		return err
	}

	// 6. 发送data
	transfer := utils.NewTransfer(p.Conn, 8096)
	err = transfer.WritePkg(resMsgBytes)

	return err
}

func (p *UserProcess) ServerProcessRegister(msg *message.Message) error {
	// 核心代码
	// 1. 先从 msg 中取出 msg.Data，并直接反序列化成LoginMsg
	var registerMsg *message.RegisterMsg
	err := json.Unmarshal([]byte(msg.Data), &registerMsg)
	if err != nil {
		log.Println("[server]unmarshal err=", err)
		return err
	}

	// 先声明一个 resMsg
	var resMsg = &message.Message{}
	resMsg.Type = message.RegisterResMsgType

	// 在声明一个 LoginResMsg
	var registerResMsg = &message.RegisterResMsg{}

	// 我们需要到redis数据库去完成验证
	// 1. 使用model.MyUserDao 到redis去验证
	err = model.MyUserDao.Register(&registerMsg.User)
	if err != nil {
		if err == model.ERROR_USER_EXISTS {
			registerResMsg.Code = 505
			registerResMsg.Error = err.Error()
		} else {
			registerResMsg.Code = 506
			registerResMsg.Error = "服务器内部错误"
		}
	} else {
		registerResMsg.Code = 200
	}

	// 3. 将 loginResMsg 序列化
	registerResMsgBytes, err := json.Marshal(registerResMsg)
	if err != nil {
		log.Println("[server]marshal err=", err)
		return err
	}

	// 4 将data赋值给 resMsg
	resMsg.Data = string(registerResMsgBytes)

	// 5. 对 resMsg 进行序列化，准备发送
	resMsgBytes, err := json.Marshal(resMsg)
	if err != nil {
		log.Println("[server]marshal err=", err)
		return err
	}

	// 6. 发送data
	transfer := utils.NewTransfer(p.Conn, 8096)
	err = transfer.WritePkg(resMsgBytes)

	return err
}

func (p *UserProcess) NotifyOthersOnlineUsers(userId int) {
	// 遍历 onlineUsers, 然后一个一个的发送 NotifyUserStatusMsg
	for id, process := range UserMgr.OnlineUsers {
		if id == userId {
			continue
		}
		// 开始通知
		process.NotifyMeOnline(userId)
	}
}

func (p *UserProcess) NotifyMeOnline(userId int) error {
	// 组装我们的NotifyUserStatusMsg
	var msg = &message.Message{}
	msg.Type = message.NotifyUserStatusMsgType

	var notifyUserStatusMsg = &message.NotifyUserStatusMsg{}
	notifyUserStatusMsg.UserId = userId
	notifyUserStatusMsg.Status = message.UserOnline

	// 将notifyUserStatusMsg序列化
	notifyUserStatusMsgBytes, err := json.Marshal(notifyUserStatusMsg)
	if err != nil {
		log.Println("[server]marshal err=", err)
		return err
	}

	// 将序列化后的notifyUserStatusMsg 赋值给 msg.Data
	msg.Data = string(notifyUserStatusMsgBytes)

	msgBytes, err := json.Marshal(msg)
	if err != nil {
		log.Println("[server]marshal err=", err)
		return err
	}

	transfer := utils.NewTransfer(p.Conn, 8096)
	err = transfer.WritePkg(msgBytes)

	return err
}
