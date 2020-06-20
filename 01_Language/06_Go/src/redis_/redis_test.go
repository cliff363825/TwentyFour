package redis_

import (
	"fmt"
	"github.com/garyburd/redigo/redis"
	"testing"
)

func TestRedis1(t *testing.T) {
	// 通过 go 向 redis 写入数据和读取数据
	// 1. 连接到redis
	conn, err := redis.Dial("tcp", "127.0.0.1:6379")
	if err != nil {
		fmt.Println("redis err=", err)
		return
	}
	fmt.Println("redis连接成功，资源类型使用完后需要进行关闭")
	defer conn.Close()

	fmt.Printf("redis conn=(%T)%v\n", conn, conn) // redis conn=(*redis.conn)...

	// 2. 通过go向redis写入数据 string [key-val]
	_, err = conn.Do("set", "name", "tom")
	if err != nil {
		fmt.Println("set err=", err)
		return
	}

	// 3. 通过go向redis读取数据 string[key-val]
	reply, err := redis.String(conn.Do("get", "name"))
	if err != nil {
		fmt.Println("get err=", err)
		return
	}
	fmt.Printf("reply=(%T)%v\n", reply, reply)
}

func TestRedis2(t *testing.T) {
	conn, err := redis.Dial("tcp", "127.0.0.1:6379")
	if err != nil {
		fmt.Println("redis err=", err)
		return
	}
	defer conn.Close()

	conn.Do("hset", "user01", "name", "汤姆")
	conn.Do("hset", "user01", "age", "11")

	reply1, _ := redis.String(conn.Do("hget", "user01", "name"))
	reply2, _ := redis.Int(conn.Do("hget", "user01", "age"))
	fmt.Printf("reply1=(%T)%v\n", reply1, reply1) // reply1=(string)汤姆
	fmt.Printf("reply2=(%T)%v\n", reply2, reply2) // reply2=(int)11

	reply3, _ := redis.Strings(conn.Do("hmget", "user01", "name", "age"))
	fmt.Printf("reply3=(%T)%v\n", reply3, reply3) // reply3=([]string)[汤姆 11]
}

// 定义一个全局的pool
var pool *redis.Pool

// 当程序启动时，就初始化连接池
func init() {
	pool = &redis.Pool{
		MaxIdle:     8,   // 最大空闲连接数
		MaxActive:   0,   // 表示和数据库的最大连接数，0 表示没有限制
		IdleTimeout: 100, // 最大空闲时间
		Dial: func() (conn redis.Conn, e error) { // 初始化连接的代码，连接哪个ip
			return redis.Dial("tcp", "127.0.0.1:6379")
		},
	}
}

func TestRedisPool1(t *testing.T) {
	// 在程序运行结束后，关闭连接池
	defer pool.Close()

	// 先从 pool取出一个连接
	conn := pool.Get()
	defer conn.Close()
	if conn.Err() != nil {
		fmt.Println("redis err=", conn.Err())
		return
	}

	conn.Do("set", "name", "golang")
	reply, _ := redis.String(conn.Do("get", "name"))
	fmt.Printf("reply=(%T)%v\n", reply, reply) // reply=(string)golang

	// 如果我们要从pool取出连接，一定保证连接池是没有关闭
	pool.Close()

	conn2 := pool.Get()
	fmt.Printf("conn2=(%T)%v\n", conn2, conn2)
	if conn2.Err() != nil {
		fmt.Println("redis err=", conn2.Err()) // redis err= redigo: get on closed pool
		return
	}
}
