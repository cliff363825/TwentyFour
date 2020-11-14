package functions

import "syscall"

func IsWritable(filename string) bool {
	return syscall.Access(filename, 0x2) == nil
}
