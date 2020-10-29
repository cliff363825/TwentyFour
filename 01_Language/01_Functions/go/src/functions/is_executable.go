package functions

import (
	"syscall"
)

func IsExecutable(filename string) bool {
	return syscall.Access(filename, 0x1) == nil
}
