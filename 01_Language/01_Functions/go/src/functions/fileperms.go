package functions

import (
	"os"
	"syscall"
)

func Fileperms(filename string) uint16 {
	if fileInfo, err := os.Stat(filename); err == nil {
		return fileInfo.Sys().(*syscall.Stat_t).Mode
	}
	return 0
}
