package datatype

import (
	"encoding/json"
	"fmt"
	"testing"
)

type Cat struct {
	Name   string
	Age    int
	Color  string
	Hobby  string
	Scores [3]int
}

// 给Cat类型绑定方法
func (cat Cat) MyName() {
	fmt.Printf("My name is %v\n", cat.Name)
}

func TestStruct1(t *testing.T) {
	var cat1 Cat
	fmt.Printf("cat1=(%T)%v\n", cat1, cat1) // cat1=(datatype.Cat){ 0   [0 0 0]}

	cat1.Name = "小白"
	cat1.Age = 3
	cat1.Color = "白色"
	cat1.Hobby = "吃鱼"
	fmt.Printf("cat1=(%T)%v\n", cat1, cat1) // cat1=(datatype.Cat){小白 3 白色 吃鱼 [0 0 0]}
}

type Person struct {
	Name     string              // ""
	Age      int                 // 0
	Scores   [5]float64          // [0,0,0,0,0]
	ptr      *int                // nil
	slice    []int               // nil
	map1     map[string]string   // nil
	sliceMap []map[string]string // nil
	secret   interface{}         // nil
}

type Monster struct {
	Name  string `json:"name"` // `json:"name"` 就是 struct tag
	Age   int    `json:"age"`
	Skill string `json:"skill"`
}

func TestStruct2(t *testing.T) {
	var p1 Person
	fmt.Printf("p1=(%T)%v\n", p1, p1)

	if p1.ptr == nil {
		fmt.Println("p1.ptr is nil")
	}

	if p1.slice == nil {
		fmt.Println("p1.slice is nil")
	}

	if p1.map1 == nil {
		fmt.Println("p1.map1 is nil")
	}

	// 使用 slice，一定要 make 或者 append
	//p1.slice[0] = 100 // panic: runtime error: index out of range
	p1.slice = make([]int, 10)
	p1.slice[0] = 100

	// 使用map，一定要先make
	//p1.map1["key1"] = "tom" // panic: assignment to entry in nil map
	p1.map1 = make(map[string]string)
	p1.map1["key1"] = "tom"

	p1.sliceMap = make([]map[string]string, 1)
	p1.sliceMap[0] = make(map[string]string)
	p1.sliceMap[0]["key2"] = "mary"

	var item map[string]string = map[string]string{"key3": "mike"}
	p1.sliceMap = append(p1.sliceMap, item)

	fmt.Printf("p1=(%T)%v\n", p1, p1)
}

func TestStruct3(t *testing.T) {
	// 不同结构体变量的字段是独立，互不影响，一个结构体变量字段的更改，
	// 不会影响另外一个，结构体是值类型
	var monster1 Monster
	monster1.Name = "牛魔王"
	monster1.Age = 500

	var monster2 = monster1
	monster2.Name = "蜘蛛精"

	fmt.Printf("monster1=(%T)%v\n", monster1, monster1)
	fmt.Printf("monster2=(%T)%v\n", monster2, monster2)
}

func TestStruct4(t *testing.T) {
	var p1 *Person = new(Person)
	fmt.Printf("p1=(%T)%v\n", p1, p1)

	// 因为p1是一个指针，因此标准的给字段赋值方式
	// (*p1).Name = "smith" 也可以这样写 p3.Name = "smith"
	// 原因: Go的设计者为了程序员使用方便，底层会对 p3.Name = "smith" 进行处理，
	// 会给 p3加上 取值运算 (*p3).Name = "smith"
	(*p1).Name = "smith"
	p1.Name = "john"

	(*p1).Age = 30
	p1.Age = 100
	fmt.Printf("p1=(%T)%v\n", p1, p1)

	// 因为 person 是一个指针，因此标准的访问字段的方式
	// (*p2).Name = "scott" <=> p2.Name = "scott"
	var p2 *Person = &Person{}
	p2.Name = "scott"
}

type A struct {
	Num int
}

type B struct {
	Num int
}

func TestStruct5(t *testing.T) {
	var a A
	var b B

	//a = b // cannot use b (type B) as type A in assignment
	a = A(b) // 可以转换，但是有要求，就是结构体的字段要完全一样（包括：名字、格式和类型）

	fmt.Printf("a=(%T)%v\n", a, a)
	fmt.Printf("b=(%T)%v\n", b, b)

	var monster Monster = Monster{
		Name: "牛魔王",
		Age:  500,
	}

	// 将monster变量序列化为 json格式字符串
	// json.Marshal 函数中使用反射
	bytes, err := json.Marshal(monster)
	if err != nil {
		fmt.Println("marshal err=", err)
	} else {
		fmt.Printf("s=(%T)%v\n", string(bytes), string(bytes))
	}
}

type integer int

func (i integer) print() {
	fmt.Printf("i=(%T)%v\n", i, i) // i=(datatype.integer)0
}

func (i *integer) change() {
	*i = *i + 1
}

func TestStruct6(t *testing.T) {
	var i integer
	i.print()
	i.change()
	fmt.Printf("i=(%T)%v\n", i, i) // i=(datatype.integer)1
}

type Circle struct {
	radius float64
}

// 值接收器
func (c Circle) SetRadiusByValueReceiver(r float64) {
	c.radius = r
}

// 指针接收器
func (c *Circle) SetRadiusByPointerReceiver(r float64) {
	c.radius = r
}

func (c Circle) Area1() float64 {
	return 3.14 * c.radius * c.radius
}

// 为了提高效率，通常我们方法和结构体的指针类型绑定
func (c *Circle) Area2() float64 {
	//return 3.14 * (*c).radius * (*c).radius 与下面效果一致
	return 3.14 * c.radius * c.radius
}

// 类似于 php 中的 __toString() 方法
func (c *Circle) String() string {
	return "这是一个半径为" + fmt.Sprintf("%f", c.radius) + "的圆"
}

func TestStruct7(t *testing.T) {
	// 创建一个 Circle 变量
	var c Circle
	c.SetRadiusByValueReceiver(4.0)
	// c area1=(float64)0, area2=(float64)0
	fmt.Printf("c area1=(%T)%v, area2=(%T)%v\n", c.Area1(), c.Area1(), c.Area2(), c.Area2())

	var c1 *Circle = &c
	c1.SetRadiusByPointerReceiver(5.0)
	// c area1=(float64)78.5, area2=(float64)78.5
	fmt.Printf("c area1=(%T)%v, area2=(%T)%v\n", c.Area1(), c.Area1(), c.Area2(), c.Area2())

	fmt.Println("c=", &c) // c= 这是一个半径为5.000000的圆
}
