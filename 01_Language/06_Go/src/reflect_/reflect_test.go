package reflect_

import (
	"fmt"
	"reflect"
	"testing"
)

type Student struct {
	Name string
	Age  int
}

func TestReflect1(t *testing.T) {
	// 1. 先定义一个 int
	var num int = 100

	// 通过反射获取传入的变量的 type
	// 1. 先获取到 reflect.Type
	rType := reflect.TypeOf(num)
	fmt.Printf("rType=(%T)%v\n", rType, rType) // rType=(*reflect.rtype)int

	// 2. 获取到 reflect.Value
	rVal := reflect.ValueOf(num)
	fmt.Printf("rVal=(%T)%v\n", rVal, rVal) // rVal=(reflect.Value)100

	// 3. 获取变量对应的Kind
	// rVal kind=(reflect.Kind)int, rType kind=(reflect.Kind)int
	fmt.Printf("rVal kind=(%T)%v, rType kind=(%T)%v\n", rVal.Kind(), rVal.Kind(), rType.Kind(), rType.Kind())

	//var num1 = 2 + rVal.Float() // panic: reflect: call of reflect.Value.Float on int Value
	var num1 = 2 + rVal.Int()
	fmt.Printf("num1=(%T)%v\n", num1, num1) // num1=(int64)102

	// 下面我们将 rVal 转成 interface{}
	iV := rVal.Interface()
	// 将 interface{} 通过断言转成需要的类型
	num2 := iV.(int)
	fmt.Printf("num2=(%T)%v\n", num2, num2) // num2=(int)100
}

func TestReflect2(t *testing.T) {
	stu := Student{
		Name: "Tom",
		Age:  20,
	}

	// 通过反射获取传入的变量的 type
	// 1. 先获取到 reflect.Type
	rType := reflect.TypeOf(stu)
	fmt.Printf("rType=(%T)%v\n", rType, rType) // rType=(*reflect.rtype)reflect_.Student

	// 2. 获取到 reflect.Value
	rVal := reflect.ValueOf(stu)
	fmt.Printf("rVal=(%T)%v\n", rVal, rVal) // rVal=(reflect.Value){Tom 20}

	// 下面我们将 rVal 转成 interface{}
	iV := rVal.Interface()
	fmt.Printf("iV=(%T)%v\n", iV, iV) // iV=(reflect_.Student){Tom 20}
	// 将 interface{} 通过断言转成需要的类型
	if stu1, ok := iV.(Student); ok {
		fmt.Printf("stu1=(%T)%v\n", stu1, stu1) // stu1=(reflect_.Student){Tom 20}
	}
}

func myReflect(b interface{}) {
	rVal := reflect.ValueOf(b)
	fmt.Printf("rVal=(%T)%v\n", rVal, rVal) // rVal=(reflect.Value)0xc000014280

	// rVal.SetInt(20) // panic: reflect: reflect.Value.SetInt using unaddressable value

	// rVal.Elem() 返回v持有的接口保管的值的Value封装，或者v持有的指针指向的值的Value封装，相当于 (*rVal)
	rVal.Elem().SetInt(20)
}

func TestReflect3(t *testing.T) {
	var num = 10
	myReflect(&num)
	fmt.Printf("num=(%T)%v\n", num, num) // num=(int)20
}

type Monster struct {
	Name  string `json:"name"`
	Age   int    `json:"age"`
	Score float32
	Sex   string
}

func (m Monster) Print() {
	fmt.Println("---start---")
	fmt.Println(m)
	fmt.Println("---end---")
}

func (m Monster) GetSum(n1 int, n2 int) int {
	return n1 + n2
}

func (m Monster) Set(name string, age int, score float32, sex string) {
	m.Name = name
	m.Age = age
	m.Score = score
	m.Sex = sex
}

func myFunc2(a interface{}) {
	// 获取 reflect.Type 类型
	rType := reflect.TypeOf(a)
	// 获取 reflect.Value 类型
	rVal := reflect.ValueOf(a)
	// 获取到 a 对应的类别
	kind := rVal.Kind()
	if kind != reflect.Struct {
		fmt.Println("expect struct")
		return
	}

	// 获取到该结构体有几个字段
	num := rVal.NumField()
	fmt.Printf("struct has %d fields\n", num)

	// 遍历结构体的所有字段
	for i := 0; i < num; i++ {
		fmt.Printf("var %v %v = %v `%v`\n", rType.Field(i).Name, rType.Field(i).Type, rVal.Field(i), rType.Field(i).Tag)
		// 获取到struct标签，注意需要通过reflect.Type来获取tag标签的值
		//tagVal := rType.Field(i).Tag.Get("json")
		// 如果该字段有tag标签就显示，否则就不显示
		//if tagVal != "" {
		//	fmt.Printf("Field %d tag = %v\n", i, tagVal)
		//}
	}

	// 获取到该结构体有多少个方法
	numOfMethod := rVal.NumMethod()
	fmt.Printf("struct has %d methods\n", numOfMethod)

	for i := 0; i < numOfMethod; i++ {
		fmt.Printf("%v\n", rType.Method(i).Type)
	}

	// 方法的排序默认是按照 函数名的排序（ASCII码）
	rVal.Method(1).Call(nil) // 获取到第二个方法

	// 调用结构体的第1个方法Method(0)
	var params []reflect.Value // 声明了 []reflect.Value
	params = append(params, reflect.ValueOf(10))
	params = append(params, reflect.ValueOf(40))
	res := rVal.Method(0).Call(params)   // 传入的参数是 []reflect.Value，返回 []reflect.Value
	fmt.Printf("res=(%T)%v\n", res, res) // 返回结果，返回的结果是 []reflect.Value
	for k, v := range res {
		fmt.Printf("res[%v]=%v\n", k, v) // res[0]=50
	}
}

func TestReflect4(t *testing.T) {
	monster := Monster{
		Name:  "黄鼠狼精",
		Age:   400,
		Score: 30.8,
	}
	myFunc2(monster)
}
