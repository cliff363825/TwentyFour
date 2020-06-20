package message

const (
	LoginMsgType            = "LoginMsg"
	LoginResMsgType         = "LoginResMsg"
	RegisterMsgType         = "RegisterMsg"
	RegisterResMsgType      = "RegisterResMsg"
	NotifyUserStatusMsgType = "NotifyUserStatusMsg"
	SmsMsgType              = "SmsMsg"
)

const (
	UserOnline = iota
	UserOffline
	UserBusy
)

type Message struct {
	Type string `json:"type"` // 消息类型
	Data string `json:"data"`
}

// 定义两个消息，后面需要在增加
type LoginMsg struct {
	UserId   int    `json:"userId"`   // 用户id
	UserPwd  string `json:"userPwd"`  // 用户密码
	UserName string `json:"userName"` // 用户名
}

type LoginResMsg struct {
	Code    int    `json:"code"` // 返回状态码 500表示该用户未注册 200表示登录成功
	UserIds []int  `json:"userIds"`
	Error   string `json:"error"` // 返回错误信息
}

type RegisterMsg struct {
	User User `json:"user"`
}

type RegisterResMsg struct {
	Code  int    `json:"code"`  // 返回状态码 200表示注册成功
	Error string `json:"error"` // 返回错误信息
}

// 为了配个服务器端推送用户状态变化的消息
type NotifyUserStatusMsg struct {
	UserId int `json:"userId"`
	Status int `json:"status"`
}

type SmsMsg struct {
	Content string `json:"content"` // 内容
	User
}
