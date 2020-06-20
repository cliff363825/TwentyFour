package builtin_

import (
	"fmt"
	"strconv"
	"strings"
	"testing"
)

func TestStringLen(t *testing.T) {
	// 统计字符串的长度，按字节 len(str)
	// golang的编码统一为utf-8
	var str = "hello北"
	// php: strlen()
	fmt.Println("str字节长度=", len(str)) // str字节长度= 8
	// php: mb_strlen()
	fmt.Println("str字符长度=", len([]rune(str))) // str字符长度= 6
}

func TestStringToRune(t *testing.T) {
	var str = "hello北京"
	// 字符串遍历，同时处理有中文的问题
	var strSlice = []rune(str)
	for i := 0; i < len(strSlice); i++ {
		fmt.Printf("字符=%c\n", strSlice[i])
	}
}

func TestStringToInt(t *testing.T) {
	// 字符串转整数：n, err := strconv.Atoi("12")
	// java: Integer.parseInt()
	n, err := strconv.Atoi("Hello")
	if err != nil {
		fmt.Println("convert err=", err)
	} else {
		fmt.Printf("n=(%T)%v\n", n, n)
	}
}

func TestIntToString(t *testing.T) {
	// 整数转字符串 str = strconv.Itoa(12345)
	// java: String.valueOf()
	str := strconv.Itoa(12345)
	fmt.Printf("str=(%T)%v\n", str, str)
}

func TestStringToByteSlice(t *testing.T) {
	// 字符串转 []byte var bytes = []byte("hello world")
	// java: "".getBytes()
	var bytes = []byte("hello world")
	fmt.Printf("bytes=(%T)%v\n", bytes, bytes)
}

func TestByteSliceToString(t *testing.T) {
	// []byte 转 字符串 str = string([]byte{97, 98, 99})
	// java: new String(bytes[], "utf-8")
	str := string([]byte{97, 98, 99})
	fmt.Printf("str=(%T)%v\n", str, str)
}

func TestStrconv(t *testing.T) {
	// 10进制转 2 8 16进制 str = strconv.FormatInt(123, 2)
	var str string
	str = strconv.FormatInt(123, 2)
	fmt.Printf("str=(%T)%v\n", str, str)

	str = strconv.FormatInt(123, 16)
	fmt.Printf("str=(%T)%v\n", str, str)
}

func TestStrings(t *testing.T) {
	var str string

	// 查到子串是否在指定的字符串中， strings.Contains("seafood")
	// php: strpos()
	b := strings.Contains("seafood", "food")
	fmt.Printf("b=(%T)%v\n", b, b)

	// 统计一个字符串有几个指定的子串，strings.Count("ceheese", "e")
	num := strings.Count("ceheese", "e")
	fmt.Printf("num=(%T)%v\n", num, num)

	// 不区分大小写的字符串比较（==是区分字母大小写的） strings.EqualFold("abc", "AbC")
	// php: strcasecmp()
	b = strings.EqualFold("abc", "Abc")
	fmt.Printf("b=(%T)%v\n", b, b)

	// 返回子串在字符串第一次出现的index值，如果没有返回-1，strings.Index("NLT_abc", "abc")
	// php: strpos()
	index := strings.Index("NTL_abc", "abc")
	fmt.Printf("index=(%T)%v\n", index, index)

	// 返回子串在字符串最后一次出现的index，如没有返回-1
	// php: strrpos()
	index = strings.LastIndex("go goland", "go")
	fmt.Printf("index=(%T)%v\n", index, index)

	// 将指定的子串替换成 另外一个子串 strings.Replace("go go hello", "go", "go语言")
	// n可以指定你希望替换几个，如果n=-1表示全部替换
	// php: str_replace()
	str = strings.Replace("go go hello", "go", "go语言", -1)
	fmt.Printf("str=(%T)%v\n", str, str)

	// 按照指定的某个字符，为分割标识，将一个字符串拆分成字符串数组
	// php: explode()
	strArr := strings.Split("hello,world,ok", ",")
	for i := 0; i < len(strArr); i++ {
		fmt.Printf("str[%v]=(%T)%v\n", i, strArr[i], strArr[i])
	}

	// 将字符串的字母进行大小写的转换
	str = "goLang Hello"
	// php: strtolower()
	lower := strings.ToLower(str)
	// php: strtoupper()
	upper := strings.ToUpper(str)
	fmt.Printf("str lower=(%T)%v,str upper=(%T)%v\n", lower, lower, upper, upper)

	// 将字符串左右两边的空格去掉，strings.Trim()
	// php: trim() ltrim() rtrim()
	str = strings.TrimSpace(" tn a lone gopher ntrn   ")
	fmt.Printf("str=(%T)%v\n", str, str)

	// 将字符串左右两边指定的字符去掉
	str = strings.Trim("! hello! ", "! ")
	// strings.TrimLeft()
	// strings.TrimRight()
	fmt.Printf("str=(%T)%v\n", str, str)

	// 判断字符串是否以指定的字符串开头
	b = strings.HasPrefix("ftp://192.168.0.1", "ftp")
	//strings.HasSuffix()
	fmt.Printf("b=(%T)%v\n", b, b)
}
