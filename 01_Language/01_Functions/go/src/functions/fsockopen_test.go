package functions

import (
	"bufio"
	"fmt"
	"log"
	"net"
	"testing"
)

func TestFsockopen(t *testing.T) {
	conn, err := net.Dial("tcp", "www.onevgo.com:80")
	if err != nil {
		log.Fatal(err)
	}
	defer conn.Close()
	s := "GET / HTTP/1.1\r\n" +
		"Host: www.onevgo.com\r\n" +
		"Connection: Close\r\n\r\n"
	fmt.Fprint(conn, s)
	//b := make([]byte, 1024)
	//n, _ := conn.Read(b)
	//fmt.Println(string(b[0:n]))
	reader := bufio.NewReader(conn)
	for {
		s, err := reader.ReadString('\n')
		if err != nil {
			break
		}
		fmt.Print(s)
	}
}
