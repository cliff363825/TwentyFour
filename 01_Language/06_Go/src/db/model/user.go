package model

import (
	"db/utils"
	"fmt"
)

type User struct {
	Id       int
	Username string
	Password string
	Email    string
}

func (user *User) AddUser() error {
	// sql语句
	var sql = "insert into user(username, password, email) values (?,?,?)"
	// 预处理
	stmt, err := utils.GetDb().Prepare(sql)
	if err != nil {
		fmt.Println("err = ", err)
		return err
	}
	defer stmt.Close()
	// 执行
	result, err := stmt.Exec("admin", "123456", "admin@admin.com")
	if err != nil {
		fmt.Println("err = ", err)
		return err
	}
	lastInsertId, _ := result.LastInsertId()
	rowsAffected, _ := result.RowsAffected()
	fmt.Println(lastInsertId, rowsAffected)
	return nil
}

func (user *User) AddUser2() error {
	// sql语句
	var sql = "insert into user(username, password, email) values (?,?,?)"
	// 预处理
	result, err := utils.GetDb().Exec(sql, "admin2", "123456", "admin2@admin.com")
	if err != nil {
		fmt.Println("err = ", err)
		return err
	}
	lastInsertId, _ := result.LastInsertId()
	rowsAffected, _ := result.RowsAffected()
	fmt.Println(lastInsertId, rowsAffected)
	return nil
}

func (user *User) GetUserById() (*User, error) {
	sql := "select id,username,password,email from user where id = ?"
	row := utils.GetDb().QueryRow(sql, user.Id)

	var (
		id       int
		username string
		password string
		email    string
	)
	if err := row.Scan(&id, &username, &password, &email); err != nil {
		fmt.Println("err = ", err)
		return nil, err
	}

	u := User{
		Id:       id,
		Username: username,
		Password: password,
		Email:    email,
	}
	return &u, nil
}

func (user *User) GetUsers() ([]*User, error) {
	var sql = "select id,username,password,email from user"
	rows, err := utils.GetDb().Query(sql)
	if err != nil {
		fmt.Println(err)
		return nil, err
	}
	defer rows.Close()

	var users []*User
	for rows.Next() {
		var (
			id       int
			username string
			password string
			email    string
			emailPtr *string
		)
		if err := rows.Scan(&id, &username, &password, &emailPtr); err != nil {
			fmt.Println("err = ", err)
			return nil, err
		}
		if emailPtr != nil {
			email = *emailPtr
		}
		u := User{
			Id:       id,
			Username: username,
			Password: password,
			Email:    email,
		}
		users = append(users, &u)
	}
	return users, nil
}
