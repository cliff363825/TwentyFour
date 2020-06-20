package goroutine

import (
	"fmt"
	"runtime"
	"strconv"
	"sync"
	"testing"
	"time"
)

func test() {
	for i := 1; i <= 10; i++ {
		fmt.Println("[test]hello world " + strconv.Itoa(i))
		time.Sleep(time.Second)
	}
}

func TestGoRoutine1(t *testing.T) {
	// 开启了一个协程
	// java: new Thread(runnable).start()
	go test()

	for i := 1; i <= 10; i++ {
		fmt.Println("[main]hello world " + strconv.Itoa(i))
		time.Sleep(time.Second)
	}
}

func TestGoRoutine2(t *testing.T) {
	cpuNum := runtime.NumCPU()
	fmt.Printf("cpuNum=(%T)%v\n", cpuNum, cpuNum)

	// 可以自己设置使用多个 cpu
	runtime.GOMAXPROCS(cpuNum - 1)
}

var (
	myMap map[int]int = make(map[int]int)
	// 声明一个全局的互斥锁
	// lock 是一个全局的互斥锁
	// sync 是包：synchronized 同步
	// Mutex: 是互斥
	lock sync.Mutex
	wg   sync.WaitGroup
)

// factorial 函数就是计算 n!，将这个结果放入到 myMap
func factorial(n int) {
	defer wg.Done()
	res := 1
	for i := 1; i <= n; i++ {
		res *= i
	}

	// 这里我们将 res 放入到 myMap
	// 加锁
	lock.Lock()
	myMap[n] = res
	// 解锁
	lock.Unlock()
}

func TestGoRoutine3(t *testing.T) {
	// 我们这里开启多个协程完成这个任务
	for i := 1; i <= 20; i++ {
		wg.Add(1)
		go factorial(i)
	}

	wg.Wait()

	// 这里我们输出结果，遍历这个结果
	for k, v := range myMap {
		fmt.Printf("myMap[%v]=(%T)%v\n", k, v, v)
	}
}

func TestGoRoutine4(t *testing.T) {
	// 1. 创建一个可以存放3个int类型的管道
	var intChan chan int
	intChan = make(chan int, 3)

	// 2. 看看intChan是什么
	fmt.Printf("intChan=(%T)%v\n", intChan, intChan)

	// 3. 向管道写入数据
	intChan <- 10
	num := 211
	intChan <- num

	intChan <- 50
	//intChan <- 98 // 注意点，当我们给管道写入数据时，不能超过其容量，fatal error: all goroutines are asleep - deadlock!

	// 打个比喻
	// 无缓冲的 就是一个送信人去你家送信，你不在家他不走，你一定要接下信，他才会走。
	// 无缓冲的 保证信能到你手上
	//
	// 有缓冲的 就是一个送信人去你家送信，把信扔到你家信箱，转身就走，除非你的信箱满了，他必须等信箱空下来。
	// 有缓冲的 保证信能进你家的邮箱

	// 4. 看看管道的长度和cap（容量）
	fmt.Printf("intChan len=%v, cap=%v\n", len(intChan), cap(intChan))

	// 5. 从管道中读取数据
	var num2 int
	num2 = <-intChan
	fmt.Println("num2=", num2)
	fmt.Printf("intChan len=%v, cap=%v\n", len(intChan), cap(intChan))

	// 6. 在没有使用协程的情况下，如果我们的管道数据已经全部取出，再取就会报告 deadlock
	num3 := <-intChan
	num4 := <-intChan
	//<- intChan // fatal error: all goroutines are asleep - deadlock!

	fmt.Println("num3=", num3, "num4=", num4)
}

func TestGoRoutine5(t *testing.T) {
	intChan := make(chan int, 3)
	intChan <- 100
	intChan <- 200

	close(intChan) // 关闭管道
	// 这时，不能够在写入数到channel
	//intChan <- 300 // panic: send on closed channel

	v, ok := <-intChan // ok 表示是否成功接收到管道中的数据，false表示管道已关闭并且管道数据已空。
	fmt.Printf("v=(%T)%v,ok=(%T)%v\n", v, v, ok, ok)
}

func TestGoRoutine6(t *testing.T) {
	intChan := make(chan int, 100)
	for i := 0; i < 100; i++ {
		intChan <- i * 2
	}

	// 在遍历时，如果channel没有关闭，则会出现deadlock的错误
	// 在遍历时，如果channel已经关闭，则会正常遍历数据，遍历完后，就会退出遍历
	close(intChan)

	for {
		v, ok := <-intChan
		if !ok {
			break
		}
		fmt.Printf("v=(%T)%v,ok=(%T)%v\n", v, v, ok, ok)
	}

	for v := range intChan {
		fmt.Printf("v=(%T)%v\n", v, v)
	}

	fmt.Printf("intChan len=%v\n", len(intChan))
}

// write data
func writeData(intChan chan int) {
	for i := 1; i <= 50; i++ {
		// 放入数据
		intChan <- i

		fmt.Printf("write data=(%T)%v\n", i, i)
	}
	// 关闭管道
	close(intChan)
}

// read data
func readData(intChan chan int, exitChan chan bool) {
	for {
		v, ok := <-intChan
		if !ok {
			// 接收到管道关闭
			break
		}

		fmt.Printf("read data=(%T)%v\n", v, v)
	}

	// readData 读取完数据后，即任务完成
	exitChan <- true
	close(exitChan)
}

func TestGoRoutine7(t *testing.T) {
	// 创建两个管道
	intChan := make(chan int, 50)
	exitChan := make(chan bool)

	go writeData(intChan)
	go readData(intChan, exitChan)

	v, ok := <-exitChan
	fmt.Printf("v=(%T)%v,ok=(%T)%v\n", v, v, ok, ok)
}

func TestGoRoutine8(t *testing.T) {
	intChan := make(chan int, 1000)
	primeChan := make(chan int, 2000) // 放入结果
	// 标识退出的管道
	exitChan := make(chan bool, 4)

	// 开启一个协程，向 intChan 放入 1-8000个数
	go func() {
		for i := 0; i < 8000; i++ {
			intChan <- i
		}
		close(intChan)
	}()

	// 开启四个协程，从intChan取出数据，并判断是否为素数，如果是，放入到primeChan
	for i := 0; i < 4; i++ {
		go func(num int) {
			for {
				v, ok := <-intChan
				if !ok {
					break
				}
				var flag = true
				for i := 2; i < v; i++ {
					if v%i == 0 { // 说明该num不是素数
						flag = false
						break
					}
				}
				if flag {
					primeChan <- v
				}
			}

			exitChan <- true
			fmt.Println("协程" + strconv.Itoa(num) + "退出")
		}(i)
	}

	go func() {
		for i := 0; i < 4; i++ {
			<-exitChan
		}
		// 当我们从 exitChan 取出了4个结果，就可以放心的关闭 primeChan
		close(primeChan)
	}()

	// 遍历我们的 primeNum，把结果取出
	for {
		v, ok := <-primeChan
		if !ok {
			break
		}

		fmt.Println("素数=", v)
	}
}

func TestGoRoutine9(t *testing.T) {
	// 管道可以声明为只读或者只写
	// 1. 在默认情况下，管道是双向的
	//var chan1 chan int // 可读可写

	// 2. 声明为只写
	var chan2 chan<- int
	chan2 = make(chan int, 3)
	chan2 <- 20
	//<-chan2 // error: receive from send-only type chan<- int
	fmt.Printf("chan2=(%T)%v\n", chan2, chan2)

	// 3. 声明为只读
	var chan3 <-chan int
	chan3 = make(chan int, 3)
	//chan3<-20 // Invalid operation: chan3<-20 (send to receive-only type <-chan int)
	fmt.Printf("chan3=(%T)%v\n", chan3, chan3)
}

func TestGoRoutine10(t *testing.T) {
	// 使用 select 可以解决从管道取数据的阻塞问题
	// 1. 定义一个管道 10个数据int
	intChan := make(chan int, 10)

	// 2. 定义一个管道 5个数据string
	stringChan := make(chan string, 5)

	go func() {
		for i := 0; i < 10; i++ {
			intChan <- i
		}
	}()

	go func() {
		for i := 0; i < 5; i++ {
			stringChan <- "hello," + strconv.Itoa(i)
			time.Sleep(time.Second)
		}
	}()

	// 传统的方法在遍历管道时，如果不关闭会阻塞而导致 deadlock

	// 问题，在实际开发中，可能我们不好确定什么时候关闭该管道
	// 可以使用 select 方式可以解决

	// 1. 除 default 外，如果只有一个 case 语句评估通过，那么就执行这个case里的语句；
	// 2. 除 default 外，如果有多个 case 语句评估通过，那么通过伪随机的方式随机选一个；
	// 3. 如果 default 外的 case 语句都没有通过评估，那么执行 default 里的语句；
	// 4. 如果没有 default，那么代码块会被阻塞，直到有一个 case 通过评估；否则一直阻塞
	go func() {
		for {
			select {
			case v := <-intChan: // 注意：这里，如果intChan一直没有关闭，不会一直阻塞而deadlock，会自动到下一个case匹配
				fmt.Printf("从intChan读取的数据=%v\n", v)
			case v := <-stringChan:
				fmt.Printf("从stringChan读取的数据=%v\n", v)
			}
		}
	}()

	time.Sleep(time.Second)
	intChan <- 100

	time.Sleep(time.Second * 10)
}

func TestGoRoutine11(t *testing.T) {
	var exitChan = make(chan bool, 2)

	go func() {
		defer func() {
			// 捕获抛出的panic
			if err := recover(); err != nil {
				fmt.Println("err=", err)
			}

			exitChan <- true
		}()

		i, _ := strconv.Atoi("0")
		fmt.Println("res=", 1/i) // panic: runtime error: integer divide by zero
	}()

	go func() {
		for i := 0; i < 10; i++ {
			fmt.Println("work...")
			time.Sleep(time.Second)
		}
		exitChan <- true
	}()

	for i := 0; i < cap(exitChan); i++ {
		<-exitChan
	}

	fmt.Println("程序执行结束")
}
