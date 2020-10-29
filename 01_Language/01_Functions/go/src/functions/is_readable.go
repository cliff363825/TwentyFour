package functions

import "syscall"

func IsReadable(filename string) bool {
	return syscall.Access(filename, 0x4) == nil
}
