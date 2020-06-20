package process

import (
	"fmt"
)

var (
	UserMgr *UserManager
)

type UserManager struct {
	OnlineUsers map[int]*UserProcess
}

// 完成对 UserManager初始化工作
func init() {
	UserMgr = &UserManager{}
	UserMgr.OnlineUsers = make(map[int]*UserProcess, 1024)
}

// 完成对 OnlineUsers 添加
func (um *UserManager) AddOnlineUser(process *UserProcess) {
	um.OnlineUsers[process.UserId] = process
}

func (um *UserManager) DelOnlineUser(userId int) {
	delete(um.OnlineUsers, userId)
}

// 返回当前所有在线的用户
func (um *UserManager) GetAllOnlineUser() map[int]*UserProcess {
	return um.OnlineUsers
}

// 根据id返回对应的值
func (um *UserManager) GetOnlineUserByUserId(userId int) (*UserProcess, error) {
	process, ok := um.OnlineUsers[userId]
	if !ok { // 说明，你要查找的这个用户，当前不在线
		err := fmt.Errorf("用户%d不存在", userId)
		return nil, err
	}
	return process, nil
}
