package datatype

import (
	"fmt"
	"sort"
	"testing"
)

func TestMap1(t *testing.T) {
	// map的声明和注意事项
	var a map[string]string
	// a["no1"] = "宋江" // panic: assignment to entry in nil map
	// 在使用map前，需要先make，make的作用就是给map分配数据空间
	a = make(map[string]string, 10)
	a["no1"] = "宋江"
	a["no2"] = "吴用"
	a["no1"] = "武松"
	a["no3"] = "吴用"
	fmt.Printf("a=(%T)%v\n", a, a)
}

func TestMap2(t *testing.T) {
	var cities map[string]string = make(map[string]string)
	cities["no1"] = "北京"
	cities["no2"] = "天津"
	cities["no3"] = "上海"
	fmt.Printf("cities=(%T)%v\n", cities, cities)
}

func TestMap3(t *testing.T) {
	var heroes map[string]string = map[string]string{
		"hero1": "宋江",
		"hero2": "卢俊义",
	}
	fmt.Printf("heroes=(%T)%v\n", heroes, heroes)
}

func TestMap4(t *testing.T) {
	var studentMap map[string]map[string]string = make(map[string]map[string]string)
	studentMap["stu01"] = map[string]string{
		"name":    "张三",
		"sex":     "男",
		"address": "深圳",
	}

	studentMap["stu02"] = make(map[string]string)
	studentMap["stu02"]["name"] = "李四"
	studentMap["stu02"]["sex"] = "女"
	studentMap["stu02"]["address"] = "广州"

	fmt.Printf("studentMap=(%T)%v\n", studentMap, studentMap)
	fmt.Printf("studentMap[stu01]=(%T)%v\n", studentMap["stu01"], studentMap["stu01"])
	fmt.Printf("studentMap[stu02]=(%T)%v\n", studentMap["stu02"], studentMap["stu02"])

	delete(studentMap, "stu01")
	fmt.Printf("studentMap=(%T)%v\n", studentMap, studentMap)
	fmt.Printf("studentMap[stu01]=(%T)%v\n", studentMap["stu01"], studentMap["stu01"])
	fmt.Printf("studentMap[stu01][name]=(%T)%v\n", studentMap["stu01"]["name"], studentMap["stu01"]["name"])

	// php: array_key_exists()
	_, ok := studentMap["stu01"]
	fmt.Printf("stu01 in studentMap?%v\n", ok) // stu01 in studentMap?false

	for k, v := range studentMap {
		for k1, v1 := range v {
			fmt.Printf("k=%v,v=%v,k1=%v,v1=%v\n", k, v, k1, v1)
		}
	}
}

func TestMap5(t *testing.T) {
	// 1. 声明一个map切片
	var monsters []map[string]string

	//if monsters[0] == nil {
	//	monsters[0] = make(map[string]string, 2)
	//	monsters[0]["name"] = "牛魔王"
	//	monsters[0]["age"] = "500"
	//}
	//
	//if monsters[1] == nil {
	//	monsters[1] = make(map[string]string, 2)
	//	monsters[1]["name"] = "玉兔精"
	//	monsters[1]["age"] = "400"
	//}

	//if monsters[2] == nil { // panic: runtime error: index out of range
	//	monsters[2] = make(map[string]string, 2)
	//	monsters[2]["name"] = "狐狸精"
	//	monsters[2]["age"] = "300"
	//}

	// 这里我们需要使用到切片的append函数，可以动态的增加monsters
	// 1. 先定义monster信息
	var monster map[string]string = map[string]string{
		"name": "新的妖怪",
		"age":  "200",
	}
	monsters = append(monsters, monster)

	var monster1 map[string]string = make(map[string]string)
	monster1["name"] = "新的妖怪1"
	monster1["age"] = "300"
	monsters = append(monsters, monster1)

	fmt.Println(monsters)
}

func TestMap6(t *testing.T) {
	// map的排序
	var map1 map[int]int = make(map[int]int, 10)
	map1[10] = 100
	map1[1] = 13
	map1[4] = 56
	map1[8] = 90

	fmt.Printf("map1=(%T)%v\n", map1, map1)

	// 如果按照map的key的排序进行排序输出
	// 1. 先将map的key 放入到切片中
	// 2. 对切片排序
	// 3. 遍历切片，然后按照key来输出map的值
	var keys []int
	for k, _ := range map1 {
		keys = append(keys, k)
	}
	// 排序
	sort.Ints(keys)
	fmt.Printf("keys=(%T)%v\n", keys, keys)

	for _, k := range keys {
		fmt.Printf("map[%v]=(%T)%v\n", k, map1[k], map1[k])
	}
}

func TestMap7(t *testing.T) {
	// map 是引用类型，遵守引用类型传递的机制，在一个函数接收map
	// 修改后，会直接修改原来的map
	var map1 map[int]int = make(map[int]int)
	map1[1] = 90
	map1[2] = 88
	map1[10] = 1
	map1[20] = 2

	fmt.Printf("map1=(%T)%v\n", map1, map1)
	modify(map1)
	fmt.Printf("map1=(%T)%v\n", map1, map1)

	var students map[string]Student = make(map[string]Student)
	students["no1"] = Student{
		Name:    "tom",
		Age:     18,
		Address: "北京",
	}
	students["no2"] = Student{
		Name:    "mary",
		Age:     17,
		Address: "上海",
	}

	for k, v := range students {
		fmt.Printf("学生的编号%v,名字%v，年龄%v，地址%v\n", k, v.Name, v.Age, v.Address)
	}
}

func modify(map1 map[int]int) {
	map1[10] = 900
}

type Student struct {
	Name    string
	Age     int
	Address string
}
