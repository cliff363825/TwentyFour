package main

import (
	"chatroom/server/model"
	process2 "chatroom/server/process"
	"fmt"
	"github.com/garyburd/redigo/redis"
	"log"
	"net"
	"time"
)

var pool *redis.Pool

func initPool(address string, maxIdle int, maxActive int, idleTimeout time.Duration) {
	pool = &redis.Pool{
		MaxIdle:     maxIdle,     // 最大空闲连接数
		MaxActive:   maxActive,   // 表示和数据库的最大连接数，0 表示没有限制
		IdleTimeout: idleTimeout, // 最大空闲时间
		Dial: func() (redis.Conn, error) { // 初始化连接的代码，连接哪个ip
			return redis.Dial("tcp", address)
		},
	}
}

// 这里我们编写一个函数，完成对 UserDao 的初始化任务
func initUserDao() {
	model.MyUserDao = model.NewUserDao(pool)
}

func init() {
	log.SetFlags(log.LstdFlags | log.Lshortfile)

	// 当服务器启动时，我们就去初始化我们的redis连接
	initPool("localhost:6379", 8, 0, 300*time.Second)
	initUserDao()
}

// 处理和客户端的通讯
func process(conn net.Conn) {
	// 这里需要延时关闭conn
	defer conn.Close()

	processor := &process2.Processor{
		Conn: conn,
	}
	err := processor.Process()
	if err != nil {
		log.Println("[server]process err=", err)
	}
}

func main() {
	// 提示信息
	fmt.Println("服务器在8889端口监听")
	listener, err := net.Listen("tcp", "0.0.0.0:8889")
	if err != nil {
		log.Println("[server]listen err=", err)
		return
	}
	// 延时关闭
	defer listener.Close()

	// 一旦监听成功，就等待客户端连接服务器
	for {
		fmt.Println("服务端等待客户端来连接")
		conn, err := listener.Accept()
		if err != nil {
			log.Println("[server]accept err=", err)
			return
		}

		// 一旦连接成功，则启动一个协程和客户端保持通讯
		go process(conn)
	}
}
