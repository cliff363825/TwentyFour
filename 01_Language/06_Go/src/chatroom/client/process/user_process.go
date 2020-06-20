package process

import (
	"chatroom/client/utils"
	"chatroom/common/message"
	"encoding/binary"
	"encoding/json"
	"fmt"
	"log"
	"net"
)

type UserProcess struct {
}

// 写一个函数，完成登录
func (p *UserProcess) Login(userId int, userPwd string) error {
	// 1. 连接到服务器
	conn, err := net.Dial("tcp", "localhost:8889")
	if err != nil {
		log.Println("[client]conn err=", err)
		return err
	}
	// 延时关闭
	defer conn.Close()

	// 2. 准备通过conn发送消息给服务器
	var reqMsg = &message.Message{}
	reqMsg.Type = message.LoginMsgType

	// 3. 创建一个 LoginMsg 结构体
	var loginMsg = &message.LoginMsg{}
	loginMsg.UserId = userId
	loginMsg.UserPwd = userPwd

	// 4. 将 LoginMsg 序列化
	loginMsgBytes, err := json.Marshal(loginMsg)
	if err != nil {
		log.Println("[client]marshal err=", err)
		return err
	}

	// 5. 把data赋给 msg.Data 字段
	reqMsg.Data = string(loginMsgBytes)

	// 6. 将 msg 进行序列化
	reqMsgBytes, err := json.Marshal(reqMsg)
	if err != nil {
		log.Println("[client]marshal err=", err)
		return err
	}

	// 7. 到这个时候，data就是我们要发送的消息
	// 7.1 先把data的长度发送给服务器
	// 先获取到 data 的长度 -> 转成一个表示长度的 byte 切片
	var reqMsgLen uint32
	reqMsgLen = uint32(len(reqMsgBytes))
	var reqMsgLenBytes = make([]byte, 4)
	// 7.2 网络序是大端的
	// 大端字节序（big-endian）：按照内存的增长方向，高位数据存储于低位内存中
	// 小端字节序（little-endian）：按照内存的增长方向，高位数据存储于高位内存中
	binary.BigEndian.PutUint32(reqMsgLenBytes, reqMsgLen)
	// 发送长度
	n, err := conn.Write(reqMsgLenBytes)
	if n != 4 || err != nil {
		log.Println("[client]write header err=", err)
		return err
	}

	// 发送消息本身
	n, err = conn.Write(reqMsgBytes)
	if err != nil {
		log.Println("[client]write body err=", err)
		return err
	}

	// --------------------------
	// 这里还需要处理服务器返回的消息
	transfer := utils.NewTransfer(conn, 8096)
	resMsg, err := transfer.ReadPkg()
	if err != nil {
		log.Println("[client]read login response message err=", err)
		return err
	}

	// 将 msg的data部分反序列成 LoginResMsg
	var loginResMsg message.LoginResMsg
	err = json.Unmarshal([]byte(resMsg.Data), &loginResMsg)
	if err != nil {
		log.Println("[client]unmarshal err=", err)
		return err
	}

	if loginResMsg.Code == 200 {
		CurUser.Conn = conn
		CurUser.UserId = userId
		CurUser.UserStatus = message.UserOnline

		//fmt.Println("登录成功")
		fmt.Println("当前在线用户列表如下")
		for _, v := range loginResMsg.UserIds {
			if v == userId {
				continue
			}

			fmt.Println("用户id=", v)
			OnlineUsers[v] = &message.User{
				UserId:     v,
				UserStatus: message.UserOnline,
			}
		}

		// 这里我们还需要在客户端启动一个协程
		// 该协程保持和服务器端的通讯，如果服务器有数据推送给客户端
		// 则接收并显示在客户端的终端
		go ServerProcessMsg(conn)

		// 显示我们登录成功的菜单
		for {
			ShowMenu()
		}
	} else {
		fmt.Println(loginResMsg.Error)
	}

	return nil
}

func (p *UserProcess) Register(userId int, userPwd string, userName string) {
	// 1. 连接到服务器
	conn, err := net.Dial("tcp", "localhost:8889")
	if err != nil {
		log.Println("[client]conn err=", err)
		return
	}
	// 延时关闭
	defer conn.Close()

	// 2. 准备通过conn发送消息给服务器
	var reqMsg = &message.Message{}
	reqMsg.Type = message.RegisterMsgType

	// 3. 创建一个 LoginMsg 结构体
	var registerMsg = &message.RegisterMsg{}
	registerMsg.User.UserId = userId
	registerMsg.User.UserPwd = userPwd
	registerMsg.User.UserName = userName

	// 4. 将 LoginMsg 序列化
	registerMsgBytes, err := json.Marshal(registerMsg)
	if err != nil {
		log.Println("[client]marshal err=", err)
		return
	}

	// 5. 把data赋给 msg.Data 字段
	reqMsg.Data = string(registerMsgBytes)

	// 6. 将 msg 进行序列化
	reqMsgBytes, err := json.Marshal(reqMsg)
	if err != nil {
		log.Println("[client]marshal err=", err)
		return
	}

	transfer := utils.NewTransfer(conn, 8096)
	err = transfer.WritePkg(reqMsgBytes)
	if err != nil {
		log.Println("[client]write pkg err=", err)
		return
	}

	resMsg, err := transfer.ReadPkg()
	if err != nil {
		log.Println("[client]read pkg err=", err)
		return
	}

	// 将 msg的data部分反序列成 LoginResMsg
	var registerResMsg *message.RegisterResMsg
	err = json.Unmarshal([]byte(resMsg.Data), &registerResMsg)
	if err != nil {
		log.Println("[client]unmarshal err=", err)
		return
	}

	if registerResMsg.Code == 200 {
		fmt.Println("注册成功，请重新登录")
	} else {
		fmt.Println(registerResMsg.Error)
	}

	return
}
