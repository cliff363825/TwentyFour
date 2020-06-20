package oop

import (
	"fmt"
	"oop/model"
	"testing"
)

func TestAbstract(t *testing.T) {
	var pwd = "666666"
	var account = model.NewAccount("gs", pwd, 100)
	if account == nil {
		fmt.Println("创建失败")
		return
	}

	fmt.Println("创建成功")

	account.Query(pwd)

	account.Deposit(200, pwd)
	account.Query(pwd)

	account.Withdraw(150, pwd)
	account.Query(pwd)
}
