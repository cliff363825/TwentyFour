package datatype

import (
	"fmt"
	"math/rand"
	"sort"
	"testing"
)

// 声明/定义一个接口
type Usb interface {
	Start()
	Stop()
}

type Phone struct {
}

// 让 Phone 实现 Usb 接口的方法
func (p *Phone) Start() {
	fmt.Println("手机开始工作。。。")
}

func (p *Phone) Stop() {
	fmt.Println("手机停止工作。。。")
}

func (p *Phone) Call() {
	fmt.Println("手机打电话。。。")
}

type Camera struct {
}

// 让 Camera 实现 Usb 接口的方法
func (c *Camera) Start() {
	fmt.Println("相机开始工作。。。")
}

func (c *Camera) Stop() {
	fmt.Println("相机停止工作。。。")
}

type Computer struct {
}

// 编写一个方法 Working，方法接收一个Usb接口类型的变量
// 只要是实现了 Usb接口（所谓实现 Usb 接口，就是指实现了 Usb 接口声明所有方法）
func (c *Computer) Working(usb Usb) {
	// 通过usb接口变量来调用Start和Stop方法
	usb.Start()
	usb.Stop()
}

// 1. 声明 Hero 结构体
type Hero struct {
	Name string
	Age  int
}

// 2. 声明一个 Hero 结构体切片类型
// option + enter -> Implement interface -> type "Interface" in sort.go...
type HeroSlice []Hero

// 3. 实现 sort.Interface 接口
/*
type Interface interface {
	// Len is the number of elements in the collection.
	Len() int
	// Less reports whether the element with
	// index i should sort before the element with index j.
	Less(i, j int) bool
	// Swap swaps the elements with indexes i and j.
	Swap(i, j int)
}
 */
func (s HeroSlice) Len() int {
	return len(s)
}

// Less 方法就是决定你使用什么标准进行排序
// 按Hero的年龄从小到大排序
func (s HeroSlice) Less(i, j int) bool {
	return s[i].Age < s[j].Age
}

func (s HeroSlice) Swap(i, j int) {
	//temp := s[i]
	//s[i] = s[j]
	//s[j] = temp
	// 下面一个话等价于上面三句话
	s[i], s[j] = s[j], s[i]
}

func TestInterface(t *testing.T) {
	computer := &Computer{}
	phone := &Phone{}
	camera := &Camera{}

	computer.Working(phone)
	computer.Working(camera)
}

func TestInterface1(t *testing.T) {
	var usb Usb
	fmt.Printf("usb=(%T)%v\n", usb, usb) // usb=(<nil>)<nil>

	var phone = &Phone{}
	usb = phone
	fmt.Printf("usb=(%T)%v\n", usb, usb) // usb=(*datatype.Phone)&{}

	var usb1 interface{} = &Camera{}
	fmt.Printf("usb1=(%T)%v\n", usb1, usb1) // usb1=(*datatype.Camera)&{}
}

func TestInterface2(t *testing.T) {
	// 先定义一个数组/切片
	var intSlice = []int{0, -1, 10, 7, 90}
	// 要求对 intSlice 切片进行排序
	// 1. 冒泡排序
	// 2. 也可以使用系统提供的方法
	sort.Ints(intSlice)
	fmt.Println(intSlice)

	// 对结构体切片进行排序
	var heroes HeroSlice
	for i := 0; i < 10; i++ {
		hero := Hero{
			Name: fmt.Sprintf("英雄_%d", i),
			Age:  rand.Intn(100),
		}
		// 将 hero append 到 heroes 切片
		heroes = append(heroes, hero)
	}

	for _, v := range heroes {
		fmt.Println(v)
	}

	sort.Sort(heroes)
	fmt.Println("---------排序后------")
	for _, v := range heroes {
		fmt.Println(v)
	}
}

func TestInterface3(t *testing.T) {
	var usbArr [3]Usb
	fmt.Printf("usbArr=(%T)%v\n", usbArr, usbArr) // usbArr=([3]datatype.Usb)[<nil> <nil> <nil>]

	usbArr[0] = &Phone{}
	usbArr[1] = &Phone{}
	usbArr[2] = &Camera{}

	for _, v := range usbArr {
		v.Start()
		usb, ok := v.(*Phone)
		if ok {
			usb.Call()
		}
		v.Stop()
	}
}

func TestInterface4(t *testing.T) {
	var a interface{}
	var b *interface{}
	var c = a
	var d interface{} = b

	fmt.Printf("a=(%T)%v,is nil?%v\n", a, a, a == nil) // a=(<nil>)<nil>,is nil?true
	fmt.Printf("b=(%T)%v,is nil?%v\n", b, b, b == nil) // b=(*interface {})<nil>,is nil?true
	fmt.Printf("c=(%T)%v,is nil?%v\n", c, c, c == nil) // c=(<nil>)<nil>,is nil?true
	fmt.Printf("d=(%T)%v,is nil?%v\n", d, d, d == nil) // d=(*interface {})<nil>,is nil?false
}

func myFunc1(a interface{}) {
	hero := a.(Hero)
	hero.Name = "bbb"
}

func TestInterface5(t *testing.T) {
	hero := Hero{}
	hero.Name = "aaa"
	var a interface{} = hero
	myFunc1(a)
	fmt.Printf("hero=(%T)%v\n", a, a) // hero=(datatype.Hero){aaa 0}
}
