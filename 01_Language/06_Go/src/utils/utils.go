package utils

import (
	"bytes"
	"fmt"
	"runtime"
	"strconv"
)

func Dump(v interface{}, opts ...interface{}) (res string) {
	var isPrint = true
	if len(opts) > 0 {
		if b, ok := opts[0].(bool); ok {
			isPrint = b
		}
	}
	res = fmt.Sprintf("(%T)%v", v, v)
	if isPrint {
		fmt.Println(res)
	}
	return
}

func Trace() {
	pc := make([]uintptr, 10)
	n := runtime.Callers(2, pc)
	frames := runtime.CallersFrames(pc[:n])
	frame, _ := frames.Next()
	fmt.Printf("%v() %v:%v\n", frame.Function, frame.File, frame.Line)
}

func GetGID() uint64 {
	b := make([]byte, 64)
	b = b[:runtime.Stack(b, false)]
	b = bytes.TrimPrefix(b, []byte("goroutine "))
	b = b[:bytes.IndexByte(b, ' ')]
	n, _ := strconv.ParseUint(string(b), 10, 64)
	return n
}
