package functions

import (
	"golang.org/x/sys/unix"
)

func IsExecutable(filename string) bool {
	if err := unix.Access(filename, unix.X_OK); err != nil {
		return false
	}
	return true
}
