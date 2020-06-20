package utils

import (
	"chatroom/common/message"
	"encoding/binary"
	"encoding/json"
	"errors"
	"log"
	"net"
)

// 这里将这些方法关联到结构体中
type Transfer struct {
	// 分析它应该有哪些字段
	Conn net.Conn
	Buf  []byte
}

func NewTransfer(conn net.Conn, bufLen int) *Transfer {
	return &Transfer{
		Conn: conn,
		Buf:  make([]byte, bufLen),
	}
}

func (t *Transfer) ReadPkg() (*message.Message, error) {
	//fmt.Println("读取客户端发送的数据")
	// conn.Read 在conn没有被关闭的情况下，才会阻塞
	// 如果客户端关闭了 conn，就不会阻塞
	_, err := t.Conn.Read(t.Buf[0:4])
	if err != nil {
		log.Println("read pkg header err=", err)
		return nil, err
	}

	// 根据 bytes[0:4] 转成一个 uint32 类型
	var msgLen uint32
	msgLen = binary.BigEndian.Uint32(t.Buf[0:4])
	if int(msgLen) > cap(t.Buf) {
		err = errors.New("read pkg len too long")
		return nil, err
	}

	// 根据 msgLen 读取消息内容
	n, err := t.Conn.Read(t.Buf[0:msgLen])
	if n != int(msgLen) || err != nil {
		log.Println("read pkg body err=", err)
		return nil, err
	}

	var msg *message.Message
	// 反序列化成 message.Message
	err = json.Unmarshal(t.Buf[0:n], &msg)
	if err != nil {
		log.Println("read pkg unmarshal err=", err)
		return nil, err
	}

	return msg, nil
}

func (t *Transfer) WritePkg(data []byte) error {
	// 先发送一个长度给对方
	var msgLen uint32
	msgLen = uint32(len(data))
	var msgLenBytes = make([]byte, 4)
	// 网络序是大端的
	// 大端字节序（big-endian）：按照内存的增长方向，高位数据存储于低位内存中
	// 小端字节序（little-endian）：按照内存的增长方向，高位数据存储于高位内存中
	binary.BigEndian.PutUint32(msgLenBytes, msgLen)
	// 发送长度
	n, err := t.Conn.Write(msgLenBytes)
	if n != 4 || err != nil {
		log.Println("write pkg header err=", err)
		return err
	}

	// 发送消息本身
	n, err = t.Conn.Write(data)
	if n != int(msgLen) || err != nil {
		log.Println("write pkg body err=", err)
		return err
	}

	return nil
}
