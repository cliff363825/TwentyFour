package functions

import (
	"fmt"
	"log"
	"os"
	"syscall"
	"testing"
	"time"
)

func TestFlock(t *testing.T) {
	file, err := os.OpenFile("test.txt", os.O_RDWR|os.O_CREATE, 0644)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	if err := syscall.Flock(int(file.Fd()), syscall.LOCK_EX); err == nil {
		defer syscall.Flock(int(file.Fd()), syscall.LOCK_UN)
		fmt.Println(time.Now())
		time.Sleep(time.Second * 10)
		fmt.Println(time.Now())
		file.Truncate(0)
		file.WriteString("hello world")
	}
}
