package functions

import (
	"fmt"
	"runtime/debug"
	"testing"
)

func TestDebugBacktrace(t *testing.T) {
	fmt.Println(string(debug.Stack()))
}

func TestDebugPrintBacktrace(t *testing.T) {
	debug.PrintStack()
}
