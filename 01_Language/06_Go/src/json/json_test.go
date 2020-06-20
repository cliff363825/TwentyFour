package json

import (
	"encoding/json"
	"fmt"
	"testing"
)

type Monster struct {
	Name     string  `json:"name"`
	Age      int     `json:"age"`
	Birthday string  `json:"birthday"`
	Salary   float64 `json:"salary"`
	Skill    string  `json:"skill"`
}

func TestJson(t *testing.T) {
	monster := &Monster{
		Name:     "牛魔王",
		Age:      500,
		Birthday: "2011-11-11",
		Salary:   8000,
		Skill:    "牛魔圈",
	}

	bytes, err := json.Marshal(monster)
	if err != nil {
		fmt.Println("marshal err=", err)
		return
	}
	fmt.Println("json=", string(bytes))
}

func TestJson1(t *testing.T) {
	var a map[string]interface{}
	a = make(map[string]interface{})
	a["name"] = "红孩儿"
	a["age"] = 30
	a["address"] = "火云洞"

	bytes, err := json.Marshal(a)
	if err != nil {
		fmt.Println("marshal err=", err)
		return
	}
	fmt.Println("json=", string(bytes))
}

func TestJson2(t *testing.T) {
	var a []map[string]interface{}

	var m1 = make(map[string]interface{})
	m1["name"] = "jack"
	m1["age"] = 7
	m1["address"] = "北京"
	a = append(a, m1)

	var m2 = make(map[string]interface{})
	m2["name"] = "tom"
	m2["age"] = 20
	m2["address"] = [2]string{"墨西哥", "夏威夷"}
	a = append(a, m2)

	bytes, err := json.Marshal(a)
	if err != nil {
		fmt.Println("marshal err=", err)
		return
	}
	fmt.Println("json=", string(bytes))
}

func TestJson3(t *testing.T) {
	//s := `{"age":500,"birthday":"2011-11-11","salary":8000,"skill":"牛魔圈"}`
	s := `null`
	var monster Monster
	e := json.Unmarshal([]byte(s), &monster)
	if e != nil {
		fmt.Println("unmarshal err=", e)
		return
	}
	fmt.Printf("monster=(%T)%v\n", monster, monster) // monster=(json.Monster){ 0  0 }
}

func TestJson4(t *testing.T) {
	s := `{"age":500,"birthday":"2011-11-11","salary":8000,"skill":"牛魔圈"}`
	var m map[string]interface{}
	e := json.Unmarshal([]byte(s), &m)
	if e != nil {
		fmt.Println("err=", e)
		return
	}
	fmt.Printf("map=(%T)%v\n", m, m) // map=(map[string]interface {})map[age:500 birthday:2011-11-11 salary:8000 skill:牛魔圈]
}

func TestJson5(t *testing.T) {
	s := `[{"address":"北京","age":7,"name":"jack"},{"address":["墨西哥","夏威夷"],"age":20,"name":"tom"}]`
	var slice []map[string]interface{}

	e := json.Unmarshal([]byte(s), &slice)
	if e != nil {
		fmt.Println("err=", e)
		return
	}
	fmt.Printf("slice=(%T)%v\n", slice, slice) // slice=([]map[string]interface {})[map[address:北京 age:7 name:jack] map[address:[墨西哥 夏威夷] age:20 name:tom]]
}
