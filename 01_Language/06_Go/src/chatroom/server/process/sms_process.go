package process

import (
	"chatroom/common/message"
	"chatroom/server/utils"
	"encoding/json"
	"log"
	"net"
)

type SmsProcess struct {
}

func (p *SmsProcess) SendGroupMsg(msg *message.Message) error {
	// 遍历服务器端的onlineUsers map[int]*UserProcess
	var smsMsg *message.SmsMsg
	err := json.Unmarshal([]byte(msg.Data), &smsMsg)
	if err != nil {
		log.Println("[server]unmarshal err=", err)
		return err
	}

	msgBytes, err := json.Marshal(msg)
	if err != nil {
		log.Println("[server]marshal err=", err)
		return err
	}

	for id, up := range UserMgr.OnlineUsers {
		// 这里，还需要过滤掉自己，即不要再发给自己
		if id == smsMsg.UserId {
			continue
		}

		p.SendMsgToEachOnlineUser(msgBytes, up.Conn)
	}

	return nil
}

func (p *SmsProcess) SendMsgToEachOnlineUser(data []byte, conn net.Conn) {
	transfer := utils.NewTransfer(conn, 8096)
	err := transfer.WritePkg(data)
	if err != nil {
		log.Println("[server]转发消息失败", err)
	}
}
