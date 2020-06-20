package model

import (
	"fmt"
	"testing"
)

func TestUser_AddUser(t *testing.T) {
	var user User
	user.AddUser()
	user.AddUser2()
}

func TestUser_GetUserById(t *testing.T) {
	user := User{
		Id: 10,
	}
	u, _ := user.GetUserById()
	fmt.Printf("%v\n", u)
}

func TestUser_GetUsers(t *testing.T) {
	var user User
	users, _ := user.GetUsers()
	for _, v := range users {
		fmt.Println(v)
	}
}
