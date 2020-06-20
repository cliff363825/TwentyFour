package main

import (
	"fmt"
	"net"
)

func process(conn net.Conn) {
	// 这里我们循环的接收客户端发送的数据
	defer conn.Close() // 关闭conn

	// 创建一个新的切片
	buf := make([]byte, 1024)
	for {
		// conn.Read(buf)
		// 1. 等待客户端通过conn发送信息
		// 2. 如果客户端没有write【发送】，那么协程就会阻塞在这里
		n, err := conn.Read(buf) // 从conn读取
		if err != nil {
			fmt.Println("[server]read err=", err)
			return
		}

		// 3. 显示客户端发送的内容到服务端的终端
		fmt.Println(string(buf[:n]))
	}

}

func main() {
	fmt.Println("[server]starting...")
	// net.Listen("tcp", "0.0.0.0:8888")
	// 1. tcp 表示使用网络协议是tcp
	// 2. 0.0.0.0:8888 表示在本地监听 8888端口
	listener, err := net.Listen("tcp", "0.0.0.0:8888")
	if err != nil {
		fmt.Println("[server]listen err=", err)
		return
	}
	defer func() {
		if err := listener.Close(); err != nil {
			fmt.Println("[server]close err=", err)
		} else {
			fmt.Println("[server]close success")
		}
	}()

	fmt.Printf("listener=(%T)%v\n", listener, listener)

	// 循环等待客户端来连接我
	for {
		conn, err := listener.Accept()
		if err != nil {
			fmt.Println("[server]accept err=", err)
			continue
		}
		fmt.Printf("[server]%v connect...\n", conn.RemoteAddr())
		fmt.Printf("conn=(%T)%v\n", conn, conn)

		//time.Sleep(time.Second * 10) // 模拟处理10s业务
		// 这里准备起一个协程，为客户端服务
		go process(conn)
	}
}
