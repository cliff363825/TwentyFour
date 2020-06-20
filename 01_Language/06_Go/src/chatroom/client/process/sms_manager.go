package process

import (
	"chatroom/common/message"
	"encoding/json"
	"fmt"
	"log"
)

func OutputGroupMsg(msg *message.Message) {
	// 1. 反序列化
	var smsMsg *message.SmsMsg
	err := json.Unmarshal([]byte(msg.Data), &smsMsg)
	if err != nil {
		log.Println("[client]unmarshal err=", err)
		return
	}

	// 显示信息
	fmt.Printf("用户[%v](ID:%v) 对大家说：%s\n", smsMsg.UserName, smsMsg.UserId, smsMsg.Content)
}
