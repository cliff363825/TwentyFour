package utils

import (
	"database/sql"
	_ "github.com/go-sql-driver/mysql"
	"log"
	"sync"
)

var INSTANCE *sql.DB

// 单例-饿汉式
func init() {
	db, err := sql.Open("mysql", "root:root@tcp(localhost:3306)/default")
	if err != nil {
		log.Fatal(err)
	}
	INSTANCE = db
}

var once sync.Once

func GetDb() *sql.DB {
	// 单例-懒汉式
	//once.Do(func() {
	//	db, err := sql.Open("mysql", "root:root@tcp(localhost:3306)/default")
	//	if err != nil {
	//		log.Fatal(err)
	//	}
	//	INSTANCE = db
	//})
	return INSTANCE
}
