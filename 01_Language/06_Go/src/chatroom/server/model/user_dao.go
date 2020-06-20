package model

import (
	"chatroom/common/message"
	"encoding/json"
	"github.com/garyburd/redigo/redis"
	"log"
)

// 我们在服务器启动后，就初始化一个userDao实例
// 把它做成全局的变量，在需要和redis操作时，就直接使用即可
var (
	MyUserDao *UserDao
)

// 定义一个UserDao结构体
// 完成对 User 结构体的各种操作
type UserDao struct {
	Pool *redis.Pool
}

// 使用工厂模式，创建一个UserDao实例
func NewUserDao(pool *redis.Pool) *UserDao {
	return &UserDao{
		Pool: pool,
	}
}

// 1. 根据用户id 返回一个 User实例 + err
func (dao *UserDao) getUserById(conn redis.Conn, id int) (*User, error) {
	// 通过给定ID 去redis查询这个用户
	userRes, err := redis.String(conn.Do("hget", "users", id))
	if err != nil {
		// 错误
		log.Println("[server]redis err=", err)
		if err == redis.ErrNil { // 表示在 users 哈希中，没有找到对应id
			err = ERROR_USER_NOTEXISTS
		}
		return nil, err
	}

	var user *User
	// 这里我们需要把 userRes 反序列化成 User 实例
	err = json.Unmarshal([]byte(userRes), &user)
	if err != nil {
		log.Println("[server]unmarshal err=", err)
		return nil, err
	}

	return user, nil
}

// 完成登录的校验 Login
// 1. Login 完成对用户的验证
// 2. 如果用户的id 和 pwd都正确，则返回一个 user 实例
// 3. 如果用户的id 或 pwd有错误，则返回对应的错误信息
func (dao *UserDao) Login(userId int, userPwd string) (*User, error) {
	// 先从 UserDao 的连接池中取出一根连接
	conn := dao.Pool.Get()
	// 延迟关闭
	defer conn.Close()

	user, err := dao.getUserById(conn, userId)
	if err != nil {
		log.Println("[server]获取用户信息失败", err)
		return nil, err
	}
	if user == nil {
		return nil, ERROR_USER_NOTEXISTS
	}

	return user, nil
}

func (dao *UserDao) Register(user *message.User) error {
	// 先从 UserDao 的连接池中取出一根连接
	conn := dao.Pool.Get()
	// 延迟关闭
	defer conn.Close()

	_, err := dao.getUserById(conn, user.UserId)
	if err == nil {
		return ERROR_USER_EXISTS
	}

	// 这是，说明id在redis还没有，则可以完成注册
	userBytes, err := json.Marshal(user)
	if err != nil {
		log.Println("[server]marshal err=", err)
		return err
	}

	_, err = conn.Do("hset", "users", user.UserId, string(userBytes))
	if err != nil {
		log.Println("[server]保存注册用户错误", err)
		return err
	}

	return nil
}
