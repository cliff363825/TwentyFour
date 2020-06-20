package builtin_

import (
	"fmt"
	"testing"
	"time"
)

func TestTime(t *testing.T) {
	// 看看日期和时间相关函数和方法使用
	// 1. 获取当前时间
	now := time.Now()
	fmt.Printf("now=(%T)%v\n", now, now)

	// 2. 通过now可以获取到年月日
	fmt.Printf("年=(%T)%v\n", now.Year(), now.Year())
	fmt.Printf("月=(%T)%v,(int)%v\n", now.Month(), now.Month(), int(now.Month()))
	fmt.Printf("日=(%T)%v\n", now.Day(), now.Day())
	fmt.Printf("时=(%T)%v\n", now.Hour(), now.Hour())
	fmt.Printf("分=(%T)%v\n", now.Minute(), now.Minute())
	fmt.Printf("秒=(%T)%v\n", now.Second(), now.Second())

	// 格式化日期时间
	fmt.Printf("当前年月日 %d-%d-%d %d:%d:%d\n",
		now.Year(), now.Month(), now.Day(), now.Hour(), now.Minute(), now.Second())

	dateStr := fmt.Sprintf("当前年月日 %d-%d-%d %d:%d:%d\n",
		now.Year(), now.Month(), now.Day(), now.Hour(), now.Minute(), now.Second())
	fmt.Println("dateStr=", dateStr)

	// 格式化日期时间的第二种方式
	fmt.Println(now.Format("2006/01/02 15:04:05"))
	fmt.Println(now.Format("2006-01-02"))
	fmt.Println(now.Format("15:04:05"))

	/*
	for i := 0; i < 100 ; i++  {
		fmt.Println(i)
		//time.Sleep(time.Second)
		time.Sleep(time.Millisecond * 100)
	}
	*/

	// Unix 和 UnixNano 的使用
	fmt.Printf("unix时间戳=(%T)%v,unixnano时间戳=(%T)%v\n", now.Unix(), now.Unix(), now.UnixNano(), now.UnixNano())
}
