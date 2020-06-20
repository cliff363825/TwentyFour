package model

import (
	"chatroom/common/message"
	"net"
)

// 因为在客户端，我们很多地方会使用到CurUser，我们将其作为一个全局
type CurUser struct {
	Conn net.Conn
	message.User
}
