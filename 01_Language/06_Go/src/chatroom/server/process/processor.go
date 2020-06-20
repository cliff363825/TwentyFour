package process

import (
	"chatroom/common/message"
	"chatroom/server/utils"
	"fmt"
	"io"
	"log"
	"net"
)

// 先创建一个 Processor 的结构体
type Processor struct {
	Conn net.Conn
}

// 编写一个ServerProcessMsg 函数
// 功能：根据客户端发送消息种类不同，决定调用哪个函数来处理
func (p *Processor) ServerProcessMsg(msg *message.Message) error {
	//fmt.Println("msg=", msg)
	var err error
	switch msg.Type {
	case message.LoginMsgType:
		// 处理登录
		userProcess := UserProcess{
			Conn: p.Conn,
		}
		err = userProcess.ServerProcessLogin(msg)
	case message.RegisterMsgType:
		// 处理注册
		userProcess := UserProcess{
			Conn: p.Conn,
		}
		err = userProcess.ServerProcessRegister(msg)
	case message.SmsMsgType:
		smsProcess := SmsProcess{}
		err = smsProcess.SendGroupMsg(msg)
	default:
		fmt.Println("消息类型不存在")
	}
	return err
}

// 处理和客户端的通讯
func (p *Processor) Process() error {
	// 这里需要延时关闭conn
	defer p.Conn.Close()

	transfer := utils.NewTransfer(p.Conn, 8096)
	// 读客户端发送的信息
	for {
		// 这里我们将读取数据包，直接封装成一个函数readPkg(),返回Message, Error
		msg, err := transfer.ReadPkg()
		if err != nil {
			if err == io.EOF {
				fmt.Println("客户端断开连接", err)
			} else {
				log.Println("[server]read pkg err=", err)
			}
			return err
		}

		err = p.ServerProcessMsg(msg)
		if err != nil {
			return err
		}
	}
}
