package process

import (
	"chatroom/client/utils"
	"chatroom/common/message"
	"encoding/json"
	"log"
)

type SmsProcess struct {
}

func (p *SmsProcess) SendGroupMsg(content string) error {
	// 1. 创建一个 Message
	var msg = &message.Message{}
	msg.Type = message.SmsMsgType

	// 2. 创建一个SmsMsg 实例
	var smsMsg = &message.SmsMsg{}
	smsMsg.Content = content
	smsMsg.UserId = CurUser.UserId
	smsMsg.UserStatus = CurUser.UserStatus

	// 3. 序列化 smsMsg
	smsMsgBytes, err := json.Marshal(smsMsg)
	if err != nil {
		log.Println("[client]marshal err=", err)
		return err
	}

	msg.Data = string(smsMsgBytes)

	// 4. 对 Msg再次序列化
	msgBytes, err := json.Marshal(msg)
	if err != nil {
		log.Println("[client]marshal err=", err)
		return err
	}

	transfer := utils.NewTransfer(CurUser.Conn, 8096)
	err = transfer.WritePkg(msgBytes)
	if err != nil {
		log.Println("[client]send err=", err)
		return err
	}

	return nil
}
