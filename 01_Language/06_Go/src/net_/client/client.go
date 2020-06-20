package main

import (
	"bufio"
	"fmt"
	"net"
	"os"
	"strings"
)

func main() {
	fmt.Println("[client]connecting...")
	conn, err := net.Dial("tcp", "127.0.0.1:8888")
	if err != nil {
		fmt.Println("[client]dial err=", err)
		return
	}

	fmt.Println("[client]connect success")
	fmt.Printf("conn=(%T)%v\n", conn, conn)

	// 功能一：客户端可以发送单行数据，然后就退出
	// php: php://stdin
	reader := bufio.NewReader(os.Stdin) // os.Stdin 代表标准输入【终端】

	for {
		// 从终端读取一行用户输入，并准备发送给服务器
		s, err := reader.ReadString('\n')
		if err != nil {
			fmt.Println("[client]reader err=", err)
			return
		}

		s = strings.Trim(s, "\n")
		if s == "exit" {
			break
		}

		// 再将line发送给服务器
		n, err := conn.Write([]byte(s))
		if err != nil {
			fmt.Println("[client]write err=", err)
			return
		}

		fmt.Printf("[client]send %v byte(s)\n", n)
	}
}
